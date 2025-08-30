package tablice;

public class Main {
    public static void main(String[] args) {
        //task 1
        firstArray();
        //task 2
        namesArray();
    }

    private static void firstArray() {
        int[] array = {5, 10, 15, 20, 25};
        for (int i : array) {
            System.out.println(i);
        }
        }
    private static void namesArray() {
        String[] names = {"Marcin", "Mateusz", "Krystian"};
        for (String name : names) {
            System.out.println("Cześć, " + name + "!");
        }
    }
}

