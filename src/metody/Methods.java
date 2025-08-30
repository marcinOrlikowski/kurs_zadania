package metody;

public class Methods {
    public static void main(String[] args) {
        //task 11
        System.out.println("Max value in array is: " + arrayMax(new int[]{5, 10, 15}));
        System.out.println("Max value in array is: " + arrayMax(new int[]{30, 10, 15}));
        System.out.println("Max value in array is: " + arrayMax(new int[]{30, 999, 15}));
        /**
         * Output:
         * Max value in array is: 15
         * Max value in array is: 30
         * Max value in array is: 999
         */
    }

    private static int arrayMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

}

