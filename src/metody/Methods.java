package metody;

public class Methods {
    public static void main(String[] args) {
        //task 5
        double result = average(5.0, 3.0, 4.0);
        System.out.printf("Average: %.2f", result);
        /**
         * Output:
         * Average: 4,00
         */
    }

    private static double average(double a, double b, double c) {
        return (a + b + c) / 3;
    }

}

