package metody;

public class Methods {
    public static void main(String[] args) {
        //task 7
        int x = 5;
        changeX(x);
        System.out.println("Value of 'x' in was not changed and still is: " + x);
        /**
         * Output:
         * Value of 'x' changed locally in method: 10
         * Value of 'x' in was not changed and still is: 5
         */
    }

    private static void changeX(int x) {
        x = 10;
        System.out.println("Value of 'x' changed locally in method: " + x);
    }

}

