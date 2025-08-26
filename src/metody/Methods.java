package metody;

import java.util.Scanner;

public class Methods {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //task 19
        menu();
    }

    private static void menu() {
        boolean exit = false;
        do {
            System.out.println("1 - add, 2 - subtract, 3 - multiply, 0 - exit");
            switch (sc.nextInt()) {
                case 1 -> {
                    int[] numbers = readNumbers();
                    add(numbers[0], numbers[1]);
                }
                case 2 -> {
                    int[] numbers = readNumbers();
                    subtract(numbers[0], numbers[1]);
                }
                case 3 -> {
                    int[] numbers = readNumbers();
                    multiply(numbers[0], numbers[1]);
                }
                case 0 -> {
                    System.out.println("See you!");
                    exit = true;
                    sc.close();
                }
                default -> System.out.println("Wrong option");
            }
        } while (!exit);
    }

    private static int[] readNumbers() {
        System.out.println("Enter first number");
        int a = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter second number");
        int b = sc.nextInt();
        sc.nextLine();
        return new int[]{a, b};
    }

    private static void multiply(int a, int b) {
        System.out.println(a * b);
    }

    private static void subtract(int a, int b) {
        System.out.println(a - b);
    }

    private static void add(int a, int b) {
        System.out.println(a + b);
    }

}

