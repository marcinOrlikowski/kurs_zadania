package tablice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //task 8
        valueCounter();
    }

    private static void valueCounter() {
        Scanner sc = new Scanner(System.in);
        int[] array = createAndFillArray();
        System.out.println("Enter searched value:");
        int searched = sc.nextInt();
        int counter = 0;
        for (int i : array) {
            if (i == searched){
                counter++;
            }
        }
        System.out.println("Searched value appears " + counter + " times in this array");

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
}
