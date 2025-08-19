package tablice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //task 8
        isValueInArray();
    }

    private static void isValueInArray() {
        Scanner sc = new Scanner(System.in);
        int[] array = createAndFillArray();
        System.out.println("Enter searched value:");
        int searched = sc.nextInt();
        boolean found = false;
        for (int i : array) {
            if (i == searched){
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
}
