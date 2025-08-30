package tablice;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //task 14
        matrix();
    }

    private static void matrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
