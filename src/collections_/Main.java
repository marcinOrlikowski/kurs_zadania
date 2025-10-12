package collections_;

import generics.Pair;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        TwoKeyMap<String, String, Integer> twoKeyHashMap = new NestedTwoKeyHashMap<>();

        System.out.println("Is the map empty? " + twoKeyHashMap.isEmpty());

        System.out.println("\nAdding 100 elements to the map...");
        for (int i = 0; i < 100; i++) {
            twoKeyHashMap.put("k" + i, "k" + (i + 100), i);
        }

        int size = twoKeyHashMap.size();
        System.out.println("Map size: " + size);

        System.out.println("\nChecking map contents:");
        for (int i = 0; i < 100; i++) {
            System.out.println(twoKeyHashMap.get("k" + i, "k" + (i + 100)));
        }

        System.out.println("\nIs the map empty? " + twoKeyHashMap.isEmpty());
        System.out.println("Does the map contain keys ('k0', 'k100')? " + twoKeyHashMap.containsKeys("k0", "k100"));
        System.out.println("Does the map contain value 0? " + twoKeyHashMap.containsValue(0));

        System.out.println("\nRemoving entry ('k0', 'k100')...");
        twoKeyHashMap.remove("k0", "k100");
        System.out.println("Map size after removal: " + twoKeyHashMap.size());
        System.out.println("Does the map contain keys ('k0', 'k100')? " + twoKeyHashMap.containsKeys("k0", "k100"));

        System.out.println("\nPrinting all entries from entrySet:");
        Set<TwoKeyMap.Entry<String, String, Integer>> entrySet = twoKeyHashMap.entrySet();
        entrySet.forEach(System.out::println);

        System.out.println("\n--- Testing new map ---");
        NestedTwoKeyHashMap<String, String, Integer> map = new NestedTwoKeyHashMap<>();
        map.put("A", "1", 10);
        map.put("B", "2", 20);

        Set<TwoKeyMap.Entry<String, String, Integer>> set = map.entrySet();

        System.out.println("\nContents of the set:");
        for (var e : set) {
            System.out.println(e);
        }

        // Adding a new element to the map
        map.put("C", "3", 30);
        System.out.println("\nAfter adding a new entry:");
        for (var e : set) {
            System.out.println(e); // also sees the new element!
        }

        System.out.println("\nPrinting all keys (keySet):");
        Set<Pair<String, String>> keys = map.keySet();
        for (Pair<String, String> pair : keys) {
            System.out.println(pair);
        }

        System.out.println("\nPrinting all values (values):");
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println(value);
        }

        System.out.println("\n--- Testing putAll ---");
        TwoKeyMap<String, String, Integer> map1 = new NestedTwoKeyHashMap<>();
        map1.put("a", "x", 1);
        map1.put("b", "y", 2);

        TwoKeyMap<String, String, Integer> map2 = new NestedTwoKeyHashMap<>();
        map2.put("b", "z", 20);
        map2.put("c", "z", 3);
        map2.put("b", "c", 30);

        map1.putAll(map2);

        System.out.println("\nMap contents after putAll:");
        for (TwoKeyMap.Entry<String, String, Integer> entry : map1) {
            System.out.println(entry.getKey1() + ", " + entry.getKey2() + " = " + entry.getValue());
        }

        System.out.println("\nRow method:");
        Map<String, Integer> b = map1.row("b");
        System.out.println(b);

        System.out.println("\nColumn method:");
        Map<String, Integer> z = map1.column("z");
        System.out.println(z);

        System.out.println("\nNull testing:");
        map1.put("null","abc",null);


        System.out.println("\nTest from task description");
        TwoKeyMap<String, String, Integer> grades = new NestedTwoKeyHashMap<>();
        grades.put("Alice", "Math", 5);
        grades.put("Alice", "CS", 4);
        System.out.println(grades.get("Alice", "Math")); // 5
        System.out.println(grades.row("Alice")); // {Math=5, CS=4}
    }

}
