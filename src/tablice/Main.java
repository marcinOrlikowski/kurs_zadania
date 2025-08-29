package tablice;

public class Main {
    public static void main(String[] args) {
        //task 14
        matrixSumOfRows();
    }

    private static void matrixSumOfRows() {
        int sum = 0;
        int[][] matrix = {
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int i = 0; i < matrix.length; i++) {
            sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            System.out.println("Sum in " + (i + 1) + " row is: " + sum);
        }

    }
}



