package enum_;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1");
        DayOfWeek friday = DayOfWeek.FRIDAY;
        System.out.println(friday);

        System.out.println("Task 2");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day);
        }

        System.out.println("Task 3");
        DayOfWeek monday = DayOfWeek.MONDAY;
        DayOfWeek tuesday = DayOfWeek.TUESDAY;
        System.out.println(monday == tuesday);
    }
}
