package ffwork.time;

public class Test {
    public static void main(String[] args) {
        FFDateTime valid = FFDateTime.of(2021, 2, 28, 10, 0);
        FFDateTime validByParse = FFDateTime.parse("2021-02-28T10:00");
        FFDateTime invalid = FFDateTime.of(2020, 13, 20, 10, 00); // throws IllegalArgumentException: Month has to be in 1-12 range
        FFDateTime invalidParse = FFDateTime.parse("21-2-28T10:00"); // throws IllegalArgumentException: Date has to be in "YYYY-MM-DDTHH:MM"
    }
}
