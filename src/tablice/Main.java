package tablice;

public class Main {
    public static void main(String[] args) {
        //task 20
        matrixOperations();
    }

    private static void matrixOperations() {
        int[][] matrix = createAndFillMatrix();
        System.out.println("Before switching rows:");
        printMatrix(matrix);
        //switching rows
        replaceFirstRowWithLastInMatrix(matrix);
        System.out.println("After switching rows:");
        printMatrix(matrix);
        //switching diagonals
        switchDiagonalsInMatrix(matrix);
        System.out.println("After switching diagonals:");
        printMatrix(matrix);
    }

    private static int[][] createAndFillMatrix() {
        int[][] matrix = new int[5][5];
        int startingValue = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = startingValue++;
            }
        }
        return matrix;
    }

    private static void replaceFirstRowWithLastInMatrix(int[][] matrix) {
        int[] temp = matrix[0];
        matrix[0] = matrix[matrix.length - 1];
        matrix[matrix.length - 1] = temp;
    }

    private static void switchDiagonalsInMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][matrix[i].length - j - 1];
                    matrix[i][matrix[j].length - j - 1] = temp;
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.printf("%4d", i);
            }
            System.out.println();
        }
    }
}

