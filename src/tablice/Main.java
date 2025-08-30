package tablice;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //task 12
        sumOfTwoArrays();
    }

    private static void sumOfTwoArrays() {
        int[] array1 = {5,10,15,20,25};
        int[] array2 = {5,10,15,20,25};
        int[] sum = new int[array1.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = array1[i] + array2[i];
        }
        System.out.println("Result:");
        System.out.println(Arrays.toString(sum));
    }
}
