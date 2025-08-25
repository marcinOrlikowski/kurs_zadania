package metody;

public class Methods {
    public static void main(String[] args) {
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
    }

    private static int factorialRecursive(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else {
            return n * factorialRecursive(n - 1);
        }
    }
}

