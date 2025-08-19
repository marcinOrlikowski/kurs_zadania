package tablice;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //task 11
        switchFirstWithLastInArray();
    }

    private static void switchFirstWithLastInArray() {
        int[] array = {5, 10, 15, 20, 25};
        System.out.println("Before:");
        System.out.println(Arrays.toString(array));
        int temp = array[0];
        array[0] = array[array.length - 1];
        array[array.length - 1] = temp;
        System.out.println("After switch first with last value:");
        System.out.println(Arrays.toString(array));
    }
}
