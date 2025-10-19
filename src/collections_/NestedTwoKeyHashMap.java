package collections_;

import generics.Pair;
import java.util.*;

public class NestedTwoKeyHashMap<K1, K2, V> implements TwoKeyMap<K1, K2, V> {
    private final Map<K1, Map<K2, V>> twoKeyMap = new HashMap<>();
    private int size = 0;
    
    @Override
    public V put(K1 key1, K2 key2, V value) {
        checkIfKeysAreNotNull(key1, key2);
        checkIfValueIsNotNull(value);

        Map<K2, V> innerMap = twoKeyMap.computeIfAbsent(key1, k1 -> new HashMap<>());
        V oldValue = innerMap.put(key2, value);
        if (oldValue == null) {
            size++;
        }
        return oldValue;
    }

    @Override
    public V get(K1 key1, K2 key2) {
        checkIfKeysAreNotNull(key1, key2);
        Map<K2, V> innerMap = twoKeyMap.get(key1);
        if (innerMap == null) {
            return null;
        }
        return innerMap.get(key2);
    }

    @Override
    public V remove(K1 key1, K2 key2) {
        checkIfKeysAreNotNull(key1, key2);

        Map<K2, V> innerMap = twoKeyMap.get(key1);
        if (innerMap == null) {
            return null;
        }
        V oldValue = innerMap.remove(key2);
        if (oldValue != null) {
            size--;
        }
        return oldValue;
    }

    @Override
    public boolean containsKeys(K1 key1, K2 key2) {
        checkIfKeysAreNotNull(key1, key2);
        if (twoKeyMap.containsKey(key1)) {
            Map<K2, V> innerMap = twoKeyMap.get(key1);
            return innerMap.containsKey(key2);
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        checkIfValueIsNotNull(value);
        return twoKeyMap.values().stream()
                .anyMatch(innerMap -> innerMap.containsValue(value));
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
    public Set<Entry<K1, K2, V>> entrySet() {
        Set<Entry<K1, K2, V>> entries = new HashSet<>();
        Iterator<Entry<K1, K2, V>> iterator = iterator();
        while (iterator.hasNext()) {
            Entry<K1, K2, V> next = iterator.next();
            entries.add(next);
        }
        return entries;
    }

    @Override
    public Set<Pair<K1, K2>> keySet() {
        Set<Pair<K1, K2>> keySet = new HashSet<>();
        Iterator<Entry<K1, K2, V>> iterator = iterator();
        while (iterator.hasNext()) {
            Entry<K1, K2, V> next = iterator.next();
            keySet.add(Pair.of(next.getKey1(), next.getKey2()));
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        Iterator<Entry<K1, K2, V>> iterator = iterator();
        while (iterator.hasNext()) {
            Entry<K1, K2, V> next = iterator.next();
            values.add(next.getValue());
        }
        return values;
    }

    @Override
    public void putAll(TwoKeyMap<? extends K1, ? extends K2, ? extends V> other) {
        for (Entry<? extends K1, ? extends K2, ? extends V> entry : other) {
            K1 key1 = entry.getKey1();
            K2 key2 = entry.getKey2();
            V value = entry.getValue();
            Map<K2, V> innerMap = twoKeyMap.computeIfAbsent(key1, inner -> new HashMap<>());
            V old = innerMap.put(key2, value);
            if (old == null) {
                size++;
            }
        }
    }

    @Override
    public void clear() {
        twoKeyMap.clear();
        size = 0;
    }

    @Override
    public Map<K2, V> row(K1 k1) {
        if (!twoKeyMap.containsKey(k1)) {
            throw new NoSuchElementException();
        }
        return new HashMap<>(twoKeyMap.get(k1));
    }

    @Override
    public Map<K1, V> column(K2 k2) {
        HashMap<K1, V> map = new HashMap<>();
        Iterator<Entry<K1, K2, V>> iterator = iterator();
        while (iterator.hasNext()) {
            Entry<K1, K2, V> next = iterator.next();
            if (next.getKey2().equals(k2)) {
                map.put(next.getKey1(), next.getValue());
            }
        }
        return map;
    }

    @Override
    public Iterator<Entry<K1, K2, V>> iterator() {
        return new Iterator<Entry<K1, K2, V>>() {
            Iterator<Map.Entry<K1, Map<K2, V>>> outerIterator = twoKeyMap.entrySet().iterator();
            Iterator<Map.Entry<K2, V>> innerIterator = null;
            K1 key1 = null;


            @Override
            public boolean hasNext() {
                while (isEmptyOrEnded() && outerIterator.hasNext()) {
                    Map.Entry<K1, Map<K2, V>> outerEntry = outerIterator.next();
                    key1 = outerEntry.getKey();
                    Map<K2, V> innerMap = outerEntry.getValue();
                    if (innerMap != null && !innerMap.isEmpty()) {
                        innerIterator = innerMap.entrySet().iterator();
                    }
                }
                return innerIterator != null && innerIterator.hasNext();
            }

            @Override
            public Entry<K1, K2, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Map.Entry<K2, V> next = innerIterator.next();
                return new MapEntry(key1, next.getKey(), next.getValue());
            }

            private boolean isEmptyOrEnded() {
                return innerIterator == null || !innerIterator.hasNext();
            }
        };
    }

    private static <V> void checkIfValueIsNotNull(V value) {
        Objects.requireNonNull(value, "value cannot be null");
    }

    private static <K1, K2> void checkIfKeysAreNotNull(K1 key1, K2 key2) {
        Objects.requireNonNull(key1, "key1 cannot be null");
        Objects.requireNonNull(key2, "key2 cannot be null");
    }

    private class MapEntry implements Entry<K1, K2, V> {
        K1 key1;
        K2 key2;
        V value;

        public MapEntry(K1 key1, K2 key2, V value) {
            this.key1 = key1;
            this.key2 = key2;
            this.value = value;
        }

        @Override
        public K1 getKey1() {
            return key1;
        }

        @Override
        public K2 getKey2() {
            return key2;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            checkIfValueIsNotNull(value);
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            MapEntry mapEntry = (MapEntry) o;
            return Objects.equals(key1, mapEntry.key1) && Objects.equals(key2, mapEntry.key2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key1, key2);
        }

        @Override
        public String toString() {
            return "MapEntry{" +
                    "key1=" + key1 +
                    ", key2=" + key2 +
                    ", value=" + value +
                    '}';
        }
    }
}
