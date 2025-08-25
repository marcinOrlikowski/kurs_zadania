package metody;

public class Methods {
    public static void main(String[] args) {
        //task 4
        System.out.println(isEven(49) ? "Even" : "Not even");
    }

    private static boolean isEven(int a) {
        return a % 2 == 0;
    }

}

