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
        int result = mult(5, 10);
        System.out.println(result);
        //task 3
        int result = max(9, 3);
        System.out.println(result);
    }

    private static void hello(String name) {
        System.out.println("Hello, " + name);
    }

    private static int mult(int a, int b) {
        return a * b;
    }

    private static int max(int a, int b) {
        if (a > b){
            return a;
        } else {
            return b;
        }
    }
}

