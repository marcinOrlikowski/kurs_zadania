package tablice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //task 6
        highestValue();
    }

    private static void highestValue() {
        int[] array = createAndFillArray();
        int highest = 0;
        for (int i : array) {
            if (i > highest){
                highest = i;
            }
        }
        System.out.println("Highest value in array is: " + highest);

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
