package generics;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("-- Task 1 --");
        System.out.println("**Równość i hashCode**");
        Pair<Integer, String> p1 = Pair.of(1, "a");
        Pair<Integer, String> p2 = Pair.of(1, "a");
        Pair<Integer, String> p3 = Pair.of(1, "b");
        System.out.println("p1.equals(p2) : " + p1.equals(p2));
        System.out.println("p1.equals(p3) : " + p1.equals(p3));
        System.out.println("p1.hashCode() == p2.hashCode() :  p1.hashCode() == p2.hashCode()");

        System.out.println("**Obsługa `null`**");
        Pair<Object, String> x = Pair.of(null, "x");
        System.out.println(x.first());
        System.out.println(x.toString());

//        System.out.println("**Niemutowalność**");
//        Pair<String, String> stringStringPair = Pair.of("A", "B");
//        stringStringPair.setFirst(); // Cannot find setters
//        stringStringPair.setSecond(); // Cannot find setters
//        stringStringPair.first = "v"; Cannot assign a value to final variable 'first'

        System.out.println("-- Task 2 --");
        System.out.println("**Przestawienie elementów**");
        String[] a = {"A", "B", "C"};
        System.out.println("Before");
        System.out.println(Arrays.toString(a));
        ArrayUtils.swap(a, 0, 2);
        System.out.println("After");
        System.out.println(Arrays.toString(a));

        System.out.println("**Walidacja indeksów**");
        Integer[] nums = {1, 2, 3};
//      ArrayUtils.swap(nums, -1, 0); IndexOutOfBoundsException
//      ArrayUtils.swap(nums, 0, 3); ArrayIndexOutOfBoundsException

        System.out.println("**No-op**");
        String[] y = {"X", "Y"};
        System.out.println("Before");
        System.out.println(Arrays.toString(y));
        ArrayUtils.swap(y, 1, 1);
        System.out.println("After");
        System.out.println(Arrays.toString(y));

        System.out.println("-- Task 3 --");
        System.out.println("Średnia i wariancja (Integer)");
        Stats<Integer> intStats = new Stats<>(new Integer[]{1, 2, 3, 4});
        System.out.println("Mean: " + intStats.mean());
        System.out.println("Variance: " + intStats.variance());

        System.out.println("**Porównanie średnich z tolerancją**");
        Stats<Double> xStats = new Stats<>(new Double[]{2.0, 2.0});
        Stats<Double> yStats = new Stats<>(new Double[]{2.001, 1.999});
        double eps = 0.01;
        System.out.println("Has same mean: " + xStats.hasSameMean(yStats, eps));

        System.out.println("*Pusta tablica**");
//        Stats<Double> empty = new Stats<>(new Double[]{});

        System.out.println("-- Task 4 --");
        System.out.println("**Ciąg znaków (porządek naturalny)**");
        String[] sss = {"aa", "b", "ccc"};
        System.out.println(MinMax.minMax(sss));

        System.out.println("**Liczby całkowite**");
        Integer[] integers = {5, 5, -1, 10, 10};
        System.out.println(MinMax.minMax(integers));

        System.out.println("**Pojedynczy element**");
        Integer[] integer = {42};
        System.out.println(MinMax.minMax(integer));

        System.out.println("**Elementy `null` (jeśli zabronione)**");
        Integer[] integersWithNull = {1, null, 2};
//        MinMax.minMax(integersWithNull); llegalArgumentException: Provided Array has empty elements


        System.out.println("-- Task 5 --");
        System.out.println("**Wystąpienia z `null` i duplikatami**");
        System.out.println("indexOf(null) " + ArrayUtils.indexOf(new String[]{null, "a", null, "b", "a"}, null));
        System.out.println("lastIndexOf(null) " + ArrayUtils.lastIndexOf(new String[]{null, "a", null, "b", "a"}, null));
        System.out.println("indexOf(a) " + ArrayUtils.indexOf(new String[]{null, "a", null, "b", "a"}, "a"));
        System.out.println("lastIndexOf(a)" + ArrayUtils.lastIndexOf(new String[]{null, "a", null, "b", "a"}, "a"));

        System.out.println("**Brak elementu**");
        System.out.println("No element " + ArrayUtils.indexOf(new String[]{"x", "y"}, "zzz"));

        System.out.println("-- Task 6 --");
        System.out.println("**Równe tablice z `null`**");
        System.out.println(ArrayUtils.arraysEqual(new Integer[]{1, null, 3}, new Integer[]{1, null, 3}));

        System.out.println("**Różne tablice**");
        System.out.println(ArrayUtils.arraysEqual(new Integer[]{1, null, 3}, new Integer[]{1, 2, 3}));

        System.out.println("**Obsługa `null` wejściowego**");
        System.out.println(ArrayUtils.arraysEqual(new String[]{"x"}, null));
        System.out.println(ArrayUtils.arraysEqual(null, null));

        System.out.println("-- Task 7 --");
        System.out.println("**Równość**");
        Triple<String, Integer, Boolean> t1 = Triple.of("id", 42, true);
        Triple<String, Integer, Boolean> t2 = Triple.of("id", 42, true);
        Triple<String, Integer, Boolean> t3 = Triple.of("id", 43, true);
        System.out.println("t1.equals(t2) " + t1.equals(t2));
        System.out.println("t1.equals(t3) " + t1.equals(t3));

        System.out.println("**Akcesory**");
        Triple<String, Integer, Boolean> t = Triple.of("id", 42, true);
        System.out.println("t.first() " + t.first());
        System.out.println("t.second() " + t.second());
        System.out.println("t.third() " + t.third());

        System.out.println("**`toString`**");
        Triple<String, String, String> stringTriple = Triple.of("A", "B", "C");
        System.out.println(stringTriple);
    }
}
