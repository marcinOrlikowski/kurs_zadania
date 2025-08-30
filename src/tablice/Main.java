package tablice;

public class Main {
    public static void main(String[] args) {
        //task 4
        sumArray();
    }

    private static void sumArray() {
        int[] array = {5, 10, 15, 20, 25};
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        System.out.println("Sum of values in array is: " + sum);
    }
}
