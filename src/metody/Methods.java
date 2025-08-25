package metody;

public class Methods {
    public static void main(String[] args) {
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
}

