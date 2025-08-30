package metody;

public class Methods {
    public static void main(String[] args) {
        //task 16
        System.out.println(sumDigitsRecursive(12345));
    }

    private static int sumDigitsRecursive(int n) {
        if (n == 0) {
            return 0;
        } else {
            return (n % 10) + sumDigitsRecursive(n / 10);
        }

    }

}

