package enum_;

public class Person {
    private String name;
    private DayOfWeek dayOff;

    public Person(String name, DayOfWeek dayOff) {
        this.name = name;
        this.dayOff = dayOff;
    }

    public void showDayOff() {
        System.out.println("Day off: " + dayOff);
    }
}
