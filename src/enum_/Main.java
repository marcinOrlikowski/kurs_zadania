package enum_;

public class Main {
    public static void main(String[] args) {
        //task 1
        DayOfWeek friday = DayOfWeek.FRIDAY;
        System.out.println(friday + "\n");
        //task 2
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day);
        }
    }
}
