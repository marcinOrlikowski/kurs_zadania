package tablice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        palindrome();
    }

    private static void palindrome() {
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Enter sentence to check if it is a polidrome");
        String input = sc.nextLine().replaceAll("\\s", "");
        char[] charArray = input.toCharArray();
        int size = charArray.length;
        String reversed = "";
        for (int i = 0; i < size; i++) {
            stringBuilder.append(charArray[size - i - 1]);
            reversed = stringBuilder.toString();
        }
        System.out.println((input.equalsIgnoreCase(reversed)) ? "Polidrome" : "Not polidrome");
    }
}
