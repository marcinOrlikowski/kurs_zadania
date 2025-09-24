package generics;

public class ArrayUtils {
    private ArrayUtils() {
    }

    ;

    public static <T> void swap(T[] arr, int i, int j) {
        if (arr == null) {
            throw new NullPointerException();
        }
        if (i > arr.length || j > arr.length || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i != j) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static <T> int indexOf(T[] arr, T value) {
        // pierwszy indeks wystąpienia; brak → -1
        if (arr == null) {
            throw new NullPointerException("Provided array is null");
        }
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
        if (arr == null) {
            throw new NullPointerException("Provided array is null");
        }
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
}
