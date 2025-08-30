package tablice;

public class Main {
    public static void main(String[] args) {
        //task 13
        stringToCharArray();
    }

    private static void stringToCharArray() {
        String text = "Ala ma kota";
        char[] charArray = text.toCharArray();
        for (char c : charArray) {
            System.out.println(c);
        }
    }
}
