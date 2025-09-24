package generics;

import java.util.Arrays;
import java.util.Objects;

public class MinMax {
    private MinMax() {}

    public static <T extends Comparable<? super T>> Pair<T,T> minMax(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Null reference");
        }
        if (Arrays.stream(arr).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Provided Array has empty elements");
        }
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        T min = arr[0];
        T max = arr[0];
        for (T t : arr) {
            if (t.compareTo(min) < 0) {
                min = t;
            }
            if (t.compareTo(max) > 0) {
                max = t;
            }
        }
        return Pair.of(min, max);
    }

}
