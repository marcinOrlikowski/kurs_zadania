package metody;

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
}

