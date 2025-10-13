package ffwork.time;

public class Test {
    public static void main(String[] args) {
        FFDateTime valid = FFDateTime.of(21, 2, 28, 10, 0);
        FFDateTime validByParse = FFDateTime.parse("2021-02-28T10:00");

        //validation tests:
        try {
            FFDateTime invalid = FFDateTime.of(2020, 13, 20, 10, 0); // throws IllegalArgumentException: Month has to be in 1-12 range
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            FFDateTime invalidParse = FFDateTime.parse("21-2-28T10:00"); // throws IllegalArgumentException: Date has to be in "YYYY-MM-DDTHH:MM"
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        //plusMinutes() test:
        FFDateTime oldDateTime = FFDateTime.of(2001, 1, 1, 0, 0);
        FFDateTime newDateTime = oldDateTime.plusMinutes(120);
        System.out.println("Before: " + oldDateTime);
        System.out.println("After: " + newDateTime);

        //minutesUntil() test:
        System.out.println("Minutes until: " + oldDateTime.minutesUntil(newDateTime));

        //toCompare() test:
        System.out.println(oldDateTime.compareTo(newDateTime));

        //getDaysInMonth
        int february1900 = FFDateTime.getDaysInMonth(2, 1900);
        int february2000 = FFDateTime.getDaysInMonth(2, 2000);
        int february2100 = FFDateTime.getDaysInMonth(2, 2100);
        System.out.println(february1900);
        System.out.println(february2000);
        System.out.println(february2100);

        //isLeapYear
        System.out.println(FFDateTime.isLeapYear(1900));
        System.out.println(FFDateTime.isLeapYear(2000));
        System.out.println(FFDateTime.isLeapYear(2100));
    }
}
