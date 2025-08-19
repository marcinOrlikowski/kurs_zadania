package tablice;

public class Main {
    public static void main(String[] args) {
        //task 2
        namesArray();
    }

    private static void namesArray() {
        String[] names = {"Marcin", "Mateusz", "Krystian"};
        for (String name : names) {
            System.out.println("Cześć, " + name + "!");
        }
    }
}
