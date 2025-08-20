package tablice;

public class Main {
    public static void main(String[] args) {
        //task 14
        matrixSum();
    }

    private static void matrixSum() {
        int sum = 0;
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        
        for (int[] ints : matrix) {
            for (int i : ints) {
                sum += i;
            }
        }
        System.out.println("Sum of values in matrix is: " + sum);
    }
}

