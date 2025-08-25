package metody;

public class Methods {
    public static void main(String[] args) {
        //task 10
        int[] array = {5,10,15};
        System.out.println("Sum in array is: " + sumArray(array));
        /**
         * Output:
         * Sum in array is: 30
         */
    }

    private static int sumArray(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }

}

