package metody;

import java.util.Arrays;

public class Methods {
    public static void main(String[] args) {
        //task 13
        int[] array = {5,10,15,20,25};
        System.out.println("Before reversing: " + Arrays.toString(array));
        int[] reversed = reverseArray(array);
        System.out.println("After reversing: " + Arrays.toString(reversed));
        /**
         * Output:
         * Before reversing: [5, 10, 15, 20, 25]
         * After reversing: [25, 20, 15, 10, 5]
         */
    }

    private static int[] reverseArray(int[] array) {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - i - 1];
        }
        return reversed;
    }

}

