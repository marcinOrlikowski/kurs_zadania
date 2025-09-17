package ffwork.time;

public class Test {
    public static void main(String[] args) {
        FFDateTime valid = FFDateTime.of(21, 2, 28, 10, 0);
        FFDateTime validByParse = FFDateTime.parse("2021-02-28T10:00");

        //validation tests:
        try {
            FFDateTime invalid = FFDateTime.of(2020, 13, 20, 10, 00); // throws IllegalArgumentException: Month has to be in 1-12 range
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
    }
}
