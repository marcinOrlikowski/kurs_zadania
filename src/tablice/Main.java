package tablice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //task 7
        lowestValue();
    }

    private static void lowestValue() {
        int[] array = createAndFillArray();
        int lowest = Integer.MAX_VALUE;
        for (int i : array) {
            if (i < lowest){
                lowest = i;
            }
        }
        System.out.println("Lowest value in array is: " + lowest);

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
