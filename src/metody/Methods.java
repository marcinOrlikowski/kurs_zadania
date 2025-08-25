package metody;

import java.util.Arrays;

public class Methods {
    public static void main(String[] args) {
        //task 14
        int[] array1 = {5, 15, 30};
        int[] array2 = {10, 20, 25};
        int[] mergedArray = mergeArray(array1, array2);
        System.out.println(Arrays.toString(mergedArray));
    }

    private static int[] mergeArray(int[] array1, int[] array2) {
        int size = array1.length + array2.length;
        int counter = 0;
        int[] result = new int[size];
        for (int i : array1) {
            result[counter] = i;
            counter++;
        }
        for (int j : array2) {
            result[counter] = j;
            counter++;
        }
        Arrays.sort(result);
        return result;
    }
}

