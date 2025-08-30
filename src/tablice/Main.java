package tablice;

import java.util.Scanner;

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
}
