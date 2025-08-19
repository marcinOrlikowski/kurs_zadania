package tablice;

public class Main {
    public static void main(String[] args) {
        //task 5
        arithmeticAverage();
    }

    private static void arithmeticAverage() {
        int[] array = {5, 10, 15, 20, 25};
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        int average = sum / array.length;
        System.out.printf("Average: %d", average);

    }
}
