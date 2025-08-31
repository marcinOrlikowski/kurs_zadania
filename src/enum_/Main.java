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

        System.out.println("Task 4");
        enumInSwitch();

        System.out.println("Task 5");
        System.out.println(DayOfWeek.SATURDAY.isWeekend());

        System.out.println("Task 6");
        enumWithFieldAndConstructor();

        System.out.println("Task 7");
        System.out.println(Month.AUGUST);

        System.out.println("Task 8");
        enumOrdinal();

        System.out.println("Task 9");
        holidayMonths();
    }

    private static void holidayMonths() {
        for (Month month : Month.values()) {
            if (month.isHolidaySeason()){
                System.out.println(month);
            }
        }
    }

    private static void enumOrdinal() {
        for (Month month : Month.values()) {
            System.out.println(month + " " + month.ordinal());
        }
        int i = Month.JANUARY.compareTo(Month.FEBRUARY);
        System.out.println(i);
    }

    private static void enumWithFieldAndConstructor() {
        for (Month month : Month.values()) {
            System.out.println(month + " " + month.getDays());
        }
    }

    private static void enumInSwitch() {
        switch (DayOfWeek.TUESDAY){
            case MONDAY, TUESDAY -> System.out.println("Beginning of the week");
            case WEDNESDAY, THURSDAY, FRIDAY -> System.out.println("Middle of the week");
            case SATURDAY, SUNDAY -> System.out.println("Weekend");
        }
    }
}
