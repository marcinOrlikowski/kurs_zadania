package metody;

import java.util.Arrays;
import java.util.Scanner;

public class Methods {
    public static void main(String[] args) {
        //task 1
        hello("Kacper");
        hello("Mateusz");
        hello("Krystian");
        /**
         * Output:
         * Hello, Kacper
         * Hello, Mateusz
         * Hello, Krystian
         */
        //task 2
        int result2 = mult(5, 10);
        System.out.println(result2);
        //task 3
        int result3 = max(9, 3);
        System.out.println(result3);
        //task 4
        System.out.println(isEven(49) ? "Even" : "Not even");
        //task 5
        double result = average(5.0, 3.0, 4.0);
        System.out.printf("Average: %.2f", result);
        /**
         * Output:
         * Average: 4,00
         */
        //task 6
        System.out.println("Enter age:");
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();
        sc.close();
        System.out.println(ageCategorization(age));
        /**
         * Outout:
         * Enter age:
         * 12
         * Teenager
         */
        //task 7
        int x = 5;
        changeX(x);
        System.out.println("Value of 'x' in was not changed and still is: " + x);
        /**
         * Output:
         * Value of 'x' changed locally in method: 10
         * Value of 'x' in was not changed and still is: 5
         */
        //task 8
        System.out.println(factorialIterative(4));
        System.out.println(factorialIterative(5));
        System.out.println(factorialIterative(10));
        /**
         * Output:
         * Factorial of 4 is: 24
         * Factorial of 5 is: 120
         * Factorial of 10 is: 3628800
         */
        //task 9
        System.out.println(factorialRecursive(4));
        System.out.println(factorialRecursive(5));
        System.out.println(factorialRecursive(10));
        /**
         * Output:
         * 24
         * 120
         * 3628800
         */
        //task 10
        int[] array = {5, 10, 15};
        System.out.println("Sum in array is: " + sumArray(array));
        /**
         * Output:
         * Sum in array is: 30
         */
        //task 11
        System.out.println("Max value in array is: " + arrayMax(new int[]{5, 10, 15}));
        System.out.println("Max value in array is: " + arrayMax(new int[]{30, 10, 15}));
        System.out.println("Max value in array is: " + arrayMax(new int[]{30, 999, 15}));
        /**
         * Output:
         * Max value in array is: 15
         * Max value in array is: 30
         * Max value in array is: 999
         */
        //task 12
        System.out.println(isPrime(21) ? "Prime" : "Not prime");
        //task 13
        int[] array2 = {5, 10, 15, 20, 25};
        System.out.println("Before reversing: " + Arrays.toString(array));
        int[] reversed = reverseArray(array);
        System.out.println("After reversing: " + Arrays.toString(reversed));
        /**
         * Output:
         * Before reversing: [5, 10, 15, 20, 25]
         * After reversing: [25, 20, 15, 10, 5]
         */
        //task 14
        int[] array3 = {5, 15, 30};
        int[] array4 = {10, 20, 25};
        int[] mergedArray = mergeArray(array3, array4);
        System.out.println(Arrays.toString(mergedArray));
        //task 15
        System.out.println(sumDigitsIterative(12345));
        //task 16
        System.out.println(sumDigitsRecursive(12345));
        //task 17
        System.out.println(gcdIterative(21, 49));
        //task 18
        System.out.println(gcdRecursive(21, 49));
    }

    private static void hello(String name) {
        System.out.println("Hello, " + name);
    }

    private static int mult(int a, int b) {
        return a * b;
    }

    private static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    private static boolean isEven(int a) {
        return a % 2 == 0;
    }

    private static double average(double a, double b, double c) {
        return (a + b + c) / 3;
    }

    private static String ageCategorization(int age) {
        if (age < 0 || age > 150) {
            return "Wrong age";
        } else if (age >= 65) {
            return "Senior";
        } else if (age >= 18) {
            return "Adult";
        } else if (age >= 12) {
            return "Teenager";
        } else {
            return "Child";
        }
    }

    private static void changeX(int x) {
        x = 10;
        System.out.println("Value of 'x' changed locally in method: " + x);
    }

    private static int factorialIterative(int n) {
        int result = 1;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            System.out.print("Factorial of " + n + " is: ");
            return result;
        } else {
            System.out.println("n has to be positive");
            return 0;
        }
    }

    private static int factorialRecursive(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else {
            return n * factorialRecursive(n - 1);
        }
    }

    private static int sumArray(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }

    private static int arrayMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private static boolean isPrime(int n) {
        boolean dividedByMoreThanTwoFactors = false;
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                dividedByMoreThanTwoFactors = true;
            }
        }
        return !dividedByMoreThanTwoFactors;
    }

    private static int[] reverseArray(int[] array) {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - i - 1];
        }
        return reversed;
    }

    private static int[] mergeArray(int[] array1, int[] array2) {
        int size = array1.length + array2.length;
        int counter = 0;
        int[] result = new int[size];
        for (int i : array1) {
            result[counter] = i;
            counter++;
        }
        for (int j : array2) {
            result[counter] = j;
            counter++;
        }
        Arrays.sort(result);
        return result;
    }

    private static int sumDigitsIterative(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    private static int sumDigitsRecursive(int n) {
        if (n == 0) {
            return 0;
        } else {
            return (n % 10) + sumDigitsRecursive(n / 10);
        }
    }

    private static int gcdIterative(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else
                b -= a;
        }
        return a;
    }

    private static int gcdRecursive(int a, int b) {
        if (a == b) {
            return a;
        } else if (a > b) {
            return gcdRecursive(a - b, b);
        } else {
            return gcdRecursive(a, b - a);
        }
    }
}

