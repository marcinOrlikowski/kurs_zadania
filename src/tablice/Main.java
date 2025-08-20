package tablice;

public class Main {
    public static void main(String[] args) {
        //task 19
        diagonalInMatrix();
    }

    private static void diagonalInMatrix() {
        int[][] matrix = createAndFillMatrix();
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(matrix[i][i]);
        }

    }

    private static int[][] createAndFillMatrix() {
        int[][] matrix = new int[10][10];
        int startingValue = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = startingValue++;
            }
        }
        return matrix;
    }
}
