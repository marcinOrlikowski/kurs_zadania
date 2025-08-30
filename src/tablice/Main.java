package tablice;

import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //task 1
        firstArray();
        //task 2
        namesArray();
        //task 3
        createAndFillArray();
        //task 4
        sumArray();
        //task 5
        arithmeticAverage();
        //task 6
        highestValue();
        //task 7
        lowestValue();
        //task 8
        valueCounter();
        //task 9
        isValueInArray();
        //task 10
        printArrayInReverse();
        //task 11
        switchFirstWithLastInArray();
        //task 12
        sumOfTwoArrays();
        //task 13
        stringToCharArray();
        //task 14
        matrix();
        //task 15
        matrixSum();
        //task 16
        matrixSumOfRows();
        //task 17
        matrixSumOfColumns();
        //task 18
        matrixMaxValue();
        matrixThirdMaxValue();
        //task 19
        diagonalInMatrix();
    }

    private static void firstArray() {
        int[] array = {5, 10, 15, 20, 25};
        for (int i : array) {
            System.out.println(i);
        }
    }

    private static void namesArray() {
        String[] names = {"Marcin", "Mateusz", "Krystian"};
        for (String name : names) {
            System.out.println("Cześć, " + name + "!");
        }
    }

    private static void highestValue() {
        int[] array = createAndFillArray();
        int highest = 0;
        for (int i : array) {
            if (i > highest) {
                highest = i;
            }
        }
        System.out.println("Highest value in array is: " + highest);
    }

    private static void lowestValue() {
        int[] array = createAndFillArray();
        int lowest = Integer.MAX_VALUE;
        for (int i : array) {
            if (i < lowest) {
                lowest = i;
            }
        }
        System.out.println("Lowest value in array is: " + lowest);
    }

    private static void valueCounter() {
        Scanner sc = new Scanner(System.in);
        int[] array = createAndFillArray();
        System.out.println("Enter searched value:");
        int searched = sc.nextInt();
        int counter = 0;
        for (int i : array) {
            if (i == searched) {
                counter++;
            }
        }
        System.out.println("Searched value appears " + counter + " times in this array");
    }

    private static void isValueInArray() {
        Scanner sc = new Scanner(System.in);
        int[] array = createAndFillArray();
        System.out.println("Enter searched value:");
        int searched = sc.nextInt();
        boolean found = false;
        for (int i : array) {
            if (i == searched) {
                found = true;
            }
        }
        System.out.println(found ? "Found" : "Not found");
    }

    private static int[] createAndFillArray() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of array");
        int size = sc.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            System.out.println("Enter " + (i + 1 + " value"));
            array[i] = sc.nextInt();
        }
        return array;
    }

    private static void sumArray() {
        int[] array = {5, 10, 15, 20, 25};
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        System.out.println("Sum of values in array is: " + sum);
    }

    private static void arithmeticAverage() {
        int[] array = {5, 10, 15, 20, 25};
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        int average = sum / array.length;
        System.out.printf("Average: %d", average);
    }

    private static void printArrayInReverse() {
        int[] array = {3, 7, 9, 11, 15, 20};
        System.out.println("Before reversing:");
        for (int i : array) {
            System.out.println(i);
        }
        System.out.println("After reversing:");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[array.length - i - 1]);
        }
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

    private static void sumOfTwoArrays() {
        int[] array1 = {5, 10, 15, 20, 25};
        int[] array2 = {5, 10, 15, 20, 25};
        int[] sum = new int[array1.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = array1[i] + array2[i];
        }
        System.out.println("Result:");
        System.out.println(Arrays.toString(sum));
    }

    private static void stringToCharArray() {
        String text = "Ala ma kota";
        char[] charArray = text.toCharArray();
        for (char c : charArray) {
            System.out.println(c);
        }
    }

    private static void matrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private static void matrixSum() {
        int sum = 0;
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        for (int[] ints : matrix) {
            for (int i : ints) {
                sum += i;
            }
        }
        System.out.println("Sum of values in matrix is: " + sum);
    }

    private static void matrixSumOfRows() {
        int sum = 0;
        int[][] matrix = {{4, 5, 6}, {7, 8, 9}};

        for (int i = 0; i < matrix.length; i++) {
            sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            System.out.println("Sum in " + (i + 1) + " row is: " + sum);
        }
    }

    private static void matrixSumOfColumns() {
        int sum = 0;
        int[][] matrix = {{4, 5, 6}, {7, 8, 9}};

        for (int j = 0; j < matrix[0].length; j++) {
            sum = 0;
            for (int[] ints : matrix) {
                sum += ints[j];
            }
            System.out.println("Sum of " + (j + 1) + " column is: " + sum);
        }
    }

    private static void matrixThirdMaxValue() {
        int[] coordinates = new int[2];
        int highestValue = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;
        int thirdHighest = Integer.MIN_VALUE;
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
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
                if (matrix[i][j] > thirdHighest && matrix[i][j] < secondHighest) {
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
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > highestValue) {
                    highestValue = matrix[i][j];
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
            }
        }
        System.out.println("Highest value is: " + highestValue + " at " + Arrays.toString(coordinates));
    }

    private static void diagonalInMatrix() {
        int[][] matrix = createAndFillMatrix();
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(matrix[i][i]);
        }

    }

    private static int[][] createAndFillMatrix() {
        int[][] matrix = new int[10][10];
        int startingValue = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = startingValue++;
            }
        }
        return matrix;
    }
}
