package generics;

import java.util.Objects;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> void swap(T[] arr, int i, int j) {
        Objects.requireNonNull(arr, "Array cannot be null");
        validateIndexes(arr, i, j);
        if (i != j) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static <T> int indexOf(T[] arr, T value) {
        // pierwszy indeks wystąpienia; brak → -1
        Objects.requireNonNull(arr, "Array cannot be null");
        int index = -1;
        if (value == null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (value.equals(arr[i])) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public static <T> int lastIndexOf(T[] arr, T value) {
        Objects.requireNonNull(arr, "Array cannot be null");
        int index = -1;
        if (value == null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (value.equals(arr[i])) {
                    index = i;
                }
            }
        }
        return index;
    }

    public static <T> boolean arraysEqual(T[] a, T[] b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null && b[i] == null) {
                continue;
            }
            if (a[i] == null || b[i] == null) {
                return false;
            }
            if (!a[i].equals(b[i])) {
                return false;
            }
        }
        return true;
    }

    private static <T> void validateIndexes(T[] arr, int i, int j) {
        if (i > arr.length || j > arr.length || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}
