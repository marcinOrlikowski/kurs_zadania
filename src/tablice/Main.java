package tablice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //task 3
        createAndFillArray();
    }

    private static void createAndFillArray() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of array");
        int size = sc.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            System.out.println("Enter " + (i + 1 + " value"));
            array[i] = sc.nextInt();
        }

        System.out.println("Values in array:");

        for (int i : array) {
            System.out.println(i);
        }
    }
}
