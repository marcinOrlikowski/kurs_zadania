package tablice;

public class Main {
    public static void main(String[] args) {
        //task 14
        matrixSumOfColumns();
    }

    private static void matrixSumOfColumns() {
        int sum = 0;
        int[][] matrix = {
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int j = 0; j < matrix[0].length; j++) {
            sum = 0;
            for (int[] ints : matrix) {
                sum += ints[j];
            }
            System.out.println("Sum of " + (j + 1) + " column is: " + sum);
        }
    }

}




