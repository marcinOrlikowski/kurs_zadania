package tablice;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //task 18
        matrixMaxValue();
        matrixThirdMaxValue();
    }

    private static void matrixThirdMaxValue() {
        int[] coordinates = new int[2];
        int highestValue = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;
        int thirdHighest = Integer.MIN_VALUE;
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt > highestValue) {
                    highestValue = anInt;
                }
            }
        }
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt > secondHighest && anInt < highestValue) {
                    secondHighest = anInt;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > thirdHighest && matrix[i][j] < secondHighest){
                    thirdHighest = matrix[i][j];
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
            }
        }
        System.out.println("Third higest value is " + thirdHighest + " at " + Arrays.toString(coordinates));
    }

    private static void matrixMaxValue() {
        int[] coordinates = new int[2];
        int highestValue = Integer.MIN_VALUE;
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > highestValue){
                    highestValue = matrix[i][j];
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
            }
        }
        System.out.println("Highest value is: " + highestValue + " at " + Arrays.toString(coordinates));
    }
}
