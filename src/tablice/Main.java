package tablice;

public class Main {
    public static void main(String[] args) {
        //task 10
        printArrayInReverse();
    }

    private static void printArrayInReverse() {
        int[] array = {3, 7, 9, 11, 15, 20};
        System.out.println("Before reversing:");
        for (int i : array) {
            System.out.println(i);
        }
        System.out.println("After reversing:");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[array.length - i - 1]);
        }
    }
}
