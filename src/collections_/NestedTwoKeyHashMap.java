package collections_;

import generics.Pair;

import java.util.*;

/**
 * Implementation of TwoKeyMap. Keys cannot be null.
 *
 * @param <K1> - the type of key one maintained by this map
 * @param <K2> - the type of key two maintained by this map
 * @param <V>  - the type of mapped values
 */

public class NestedTwoKeyHashMap<K1, K2, V> implements TwoKeyMap<K1, K2, V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    int size;
    int threshold;
    final float loadFactor;
    Node<K1, K2, V>[] table;

    transient Set<TwoKeyMap.Entry<K1, K2, V>> entrySet;
    transient Set<Pair<K1, K2>> keySet;
    transient Collection<V> values;

    public NestedTwoKeyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    @Override
    public V put(K1 k1, K2 k2, V value) {
        if (k1 == null || k2 == null) {
            throw new NullPointerException("Keys cannot be null");
        } else {
            return putVal(hash(k1, k2), k1, k2, value);
        }
    }

    @Override
    public V get(K1 k1, K2 k2) {
        if (k1 == null || k2 == null) {
            throw new NullPointerException("Keys cannot be null");
        } else {
            Node<K1, K2, V> e;
            return (e = getNode(k1, k2)) == null ? null : e.value;
        }
    }

    @Override
    public V remove(K1 key1, K2 key2) {
        Node<K1, K2, V> e;
        return (e = removeNode(hash(key1, key2), key1, key2, null)) == null ?
                null : e.value;
    }

    @Override
    public boolean containsKeys(K1 key1, K2 key2) {
        return getNode(key1, key2) != null;
    }

    @Override
    public boolean containsValue(V value) {
        Node<K1, K2, V>[] tab;
        V v;
        if ((tab = table) != null && size > 0) {
            for (Node<K1, K2, V> e : tab) {
                for (; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                            (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<TwoKeyMap.Entry<K1, K2, V>> entrySet() {
        Set<TwoKeyMap.Entry<K1, K2, V>> es;
        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
    }

    @Override
    public Set<Pair<K1, K2>> keySet() {
        Set<Pair<K1, K2>> ks = keySet;
        if (ks == null) {
            ks = new KeySet();
            keySet = ks;
        }
        return ks;
    }

    @Override
    public Collection<V> values() {
        Collection<V> vs = values;
        if (vs == null) {
            vs = new Values();
            values = vs;
        }
        return vs;
    }

    @Override
    public void putAll(TwoKeyMap<? extends K1, ? extends K2, ? extends V> other) {
        putMapEntries(other);
    }

    @Override
    public void clear() {
        Node<K1, K2, V>[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            Arrays.fill(tab, null);
        }
    }

    @Override
    public Map<K2, V> row(K1 k1) {
        HashMap<K2, V> map = new HashMap<>();
        EntryIterator entryIterator = new EntryIterator();
        while (entryIterator.hasNext()) {
            Entry<K1, K2, V> next = entryIterator.next();
            if (next.getKey1().equals(k1)) {
                map.put(next.getKey2(), next.getValue());
            }
        }
        return map;
    }

    @Override
    public Map<K1, V> column(K2 k2) {
        HashMap<K1, V> map = new HashMap<>();
        EntryIterator entryIterator = new EntryIterator();
        while (entryIterator.hasNext()) {
            Entry<K1, K2, V> next = entryIterator.next();
            if (next.getKey2().equals(k2)) {
                map.put(next.getKey1(), next.getValue());
            }
        }
        return map;
    }

    @Override
    public Iterator<Entry<K1, K2, V>> iterator() {
        return new EntrySet().iterator();
    }

    private V putVal(int hash, K1 key1, K2 key2, V value) {
        Node<K1, K2, V>[] tab;
        Node<K1, K2, V> p;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key1, key2, value, null);
        else {
            Node<K1, K2, V> e;
            K1 k1 = p.key1;
            K2 k2 = p.key2;
            if (p.hash == hash &&
                    ((k1 == key1 && k2 == key2) || areKeysEqual(key1, key2, k1, k2)))
                e = p;
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key1, key2, value, null);
                        break;
                    }
                    k1 = e.key1;
                    k2 = e.key2;
                    if (e.hash == hash &&
                            ((k1 == key1 && k2 == key2) || areKeysEqual(key1, key2, k1, k2)))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        if (++size > threshold)
            resize();
        return null;
    }

    private Node<K1, K2, V>[] resize() {
        Node<K1, K2, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        } else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                    (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"unchecked"})
        Node<K1, K2, V>[] newTab = (Node<K1, K2, V>[]) new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K1, K2, V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else { // preserve order
                        Node<K1, K2, V> loHead = null, loTail = null;
                        Node<K1, K2, V> hiHead = null, hiTail = null;
                        Node<K1, K2, V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            } else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    private Node<K1, K2, V> getNode(Object key1, Object key2) {
        Node<K1, K2, V>[] tab;
        Node<K1, K2, V> first, e;
        int n, hash;
        K1 k1;
        K2 k2;

        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & (hash = hash(key1, key2))]) != null) {
            k1 = first.key1;
            k2 = first.key2;
            if (first.hash == hash && // always check first node
                    ((k1 == key1 && k2 == key2) || areKeysEqual(key1, key2, k1, k2)))
                return first;
            if ((e = first.next) != null) {
                do {
                    k1 = e.key1;
                    k2 = e.key2;
                    if (e.hash == hash &&
                            ((k1 == key1 && k2 == key2) || areKeysEqual(key1, key2, k1, k2)))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    private Node<K1, K2, V> removeNode(int hash, Object key1, Object key2, Object value) {
        Node<K1, K2, V>[] tab;
        Node<K1, K2, V> p;
        int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (p = tab[index = (n - 1) & hash]) != null) {
            Node<K1, K2, V> node = null, e;
            V v;
            K1 k1 = p.key1;
            K2 k2 = p.key2;
            if (p.hash == hash &&
                    ((k1 == key1 && k2 == key2) || areKeysEqual(key1, key2, k1, k2)))
                node = p;
            else if ((e = p.next) != null) {
                do {
                    k1 = e.key1;
                    k2 = e.key2;
                    if (e.hash == hash &&
                            ((k1 == key1 && k2 == key2) ||
                                    areKeysEqual(key1, key2, k1, k2))) {
                        node = e;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);
            }
            if (node != null) {
                v = node.value;
                if (value == null || Objects.equals(value, v)) {
                    if (node == p)
                        tab[index] = node.next;
                    else
                        p.next = node.next;
                    --size;
                    return node;
                }
            }
        }
        return null;
    }

    private void putMapEntries(TwoKeyMap<? extends K1, ? extends K2, ? extends V> m) {
        int s = m.size();
        if (s > 0) {
            if (table == null) { // pre-size
                double dt = Math.ceil(s / (double) loadFactor);
                int t = ((dt < (double) MAXIMUM_CAPACITY) ?
                        (int) dt : MAXIMUM_CAPACITY);
                if (t > threshold)
                    threshold = tableSizeFor(t);
            } else {
                // Because of linked-list bucket constraints, we cannot
                // expand all at once, but can reduce total resize
                // effort by repeated doubling now vs later
                while (s > threshold && table.length < MAXIMUM_CAPACITY)
                    resize();
            }

            for (TwoKeyMap.Entry<? extends K1, ? extends K2, ? extends V> e : m.entrySet()) {
                K1 key1 = e.getKey1();
                K2 key2 = e.getKey2();
                V value = e.getValue();
                putVal(hash(key1, key2), key1, key2, value);
            }
        }
    }

    private int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private static <K1, K2> boolean areKeysEqual(Object key1, Object key2, K1 k1, K2 k2) {
        return (key1 != null && key1.equals(k1)) && (key2 != null && key2.equals(k2));
    }

    private int hash(Object key1, Object key2) {
        int h;
        return (h = (key1.hashCode() + key2.hashCode())) ^ (h >>> 16);
    }

    static class Node<K1, K2, V> implements TwoKeyMap.Entry<K1, K2, V> {
        final int hash;
        final K1 key1;
        final K2 key2;
        V value;
        Node<K1, K2, V> next;

        Node(int hash, K1 key1, K2 key2, V value, Node<K1, K2, V> next) {
            this.hash = hash;
            this.key1 = key1;
            this.key2 = key2;
            this.value = value;
            this.next = next;
        }

        public final K1 getKey1() {
            return key1;
        }

        public final K2 getKey2() {
            return key2;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key1 + " " + key2 + "=" + value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TwoKeyMap.Entry)) return false;
            TwoKeyMap.Entry<?, ?, ?> e = (TwoKeyMap.Entry<?, ?, ?>) o;
            return Objects.equals(key1, e.getKey1()) &&
                    Objects.equals(key2, e.getKey2());
        }

        @Override
        public int hashCode() {
            return Objects.hash(key1, key2);
        }
    }

    final class EntrySet extends AbstractSet<TwoKeyMap.Entry<K1, K2, V>> {
        public int size() {
            return size;
        }

        public Iterator<TwoKeyMap.Entry<K1, K2, V>> iterator() {
            return new EntryIterator();
        }
    }

    abstract class HashIterator {
        Node<K1, K2, V> next;        // next entry to return
        Node<K1, K2, V> current;     // current entry
        int index;             // current slot

        HashIterator() {
            Node<K1, K2, V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K1, K2, V> nextNode() {
            Node<K1, K2, V>[] t;
            Node<K1, K2, V> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {
                } while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }
    }

    final class KeyIterator extends HashIterator
            implements Iterator<Pair<K1, K2>> {
        public Pair<K1, K2> next() {
            Node<K1, K2, V> nextNode = nextNode();
            return Pair.of(nextNode.key1, nextNode.key2);
        }
    }

    final class ValueIterator extends HashIterator
            implements Iterator<V> {
        public V next() {
            return nextNode().value;
        }
    }

    final class EntryIterator extends HashIterator
            implements Iterator<TwoKeyMap.Entry<K1, K2, V>> {
        public TwoKeyMap.Entry<K1, K2, V> next() {
            return nextNode();
        }
    }

    final class KeySet extends AbstractSet<Pair<K1, K2>> {
        public int size() {
            return size;
        }

        public Iterator<Pair<K1, K2>> iterator() {
            return new KeyIterator();
        }
    }

    final class Values extends AbstractCollection<V> {
        public int size() {
            return size;
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    Node<K1, K2, V> newNode(int hash, K1 key1, K2 key2, V value, NestedTwoKeyHashMap.Node<K1, K2, V> next) {
        return new Node<>(hash, key1, key2, value, next);
    }
}
