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
    }

    private static void hello(String name) {
        System.out.println("Hello, " + name);
    }

}

