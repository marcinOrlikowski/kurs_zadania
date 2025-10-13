package ffwork.time;

public final class FFDateTime implements Comparable<FFDateTime> {
    public static final String DATE_TIME_FORMAT = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}";
    public static final int EPOCH_YEAR = 2000;
    public static final int YEAR_DAYS = 365;
    public static final int LEAP_YEAR_DAYS = 366;
    public static final int MINUTES_IN_DAY = 1440;
    public static final int MINUTES_IN_HOUR = 60;

    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;

    public static FFDateTime parse(String iso) {
        if (!iso.matches(DATE_TIME_FORMAT)) {
            throw new IllegalArgumentException("Date has to be in \"YYYY-MM-DDTHH:MM\" format");
        }
        String[] dateAndTime = iso.split("T");
        String[] date = dateAndTime[0].split("-");
        String[] time = dateAndTime[1].split(":");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        return new FFDateTime(year, month, day, hour, minute);
    }

    public static FFDateTime of(int year, int month, int day, int hour, int minute) {
        return new FFDateTime(year, month, day, hour, minute);
    }

    public FFDateTime plusMinutes(int minutes) {
        int newToEpochMinutes = this.toEpochMinutes() + minutes;
        int totalDays = newToEpochMinutes / MINUTES_IN_DAY;
        int remainingMinutes = newToEpochMinutes % MINUTES_IN_DAY;
        int newHour = remainingMinutes / MINUTES_IN_HOUR;
        int newMinute = remainingMinutes % MINUTES_IN_HOUR;

        int newYear = EPOCH_YEAR;
        while (totalDays >= (isLeapYear(newYear) ? LEAP_YEAR_DAYS : YEAR_DAYS)) {
            totalDays -= (isLeapYear(newYear) ? LEAP_YEAR_DAYS : YEAR_DAYS);
            newYear++;
        }
        int newMonth = 1;
        while (totalDays >= getDaysInMonth(newMonth, newYear)) {
            totalDays -= getDaysInMonth(newMonth, newYear);
            newMonth++;
        }
        int newDay = totalDays + 1;

        return new FFDateTime(newYear, newMonth, newDay, newHour, newMinute);
    }

    public int toEpochMinutes() {
        int epochYear = EPOCH_YEAR;
        int days = 0;
        if (year < epochYear) {
            return 0;
        }
        for (int i = epochYear; i < year; i++) {
            for (int j = 1; j <= 12; j++) {
                days += getDaysInMonth(j, i);
            }
        }
        for (int i = 1; i < month; i++) {
            days += getDaysInMonth(i, year);
        }
        days += day - 1;
        return (days * MINUTES_IN_DAY) + (hour * MINUTES_IN_HOUR) + minute;
    }

    public int minutesUntil(FFDateTime other) {
        return other.toEpochMinutes() - this.toEpochMinutes();
    }

    public int getHour() {
        return hour;
    }

    @Override
    public int compareTo(FFDateTime o) {
        return Integer.compare(this.toEpochMinutes(), o.toEpochMinutes());
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02dT%02d:%02d", year, month, day, hour, minute);
    }

    private FFDateTime(int year, int month, int day, int hour, int minute) {
        validateDate(year, month, day, hour, minute);
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    private void validateDate(int year, int month, int day, int hour, int minute) {
        if (year < 0) {
            throw new IllegalArgumentException("Year has to be greater than 0");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month has to be in 1-12 range");
        }
        if (day < 1 || day > getDaysInMonth(month, year)) {
            throw new IllegalArgumentException("Day has to be in 1-" + getDaysInMonth(month, year) + " range");
        }
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour has to be in 0-23 range");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minutes has to be in 0-59 range");
        }
    }

    static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 2 -> {
                return isLeapYear(year) ? 29 : 28;
            }
            case 4, 6, 9, 11 -> {
                return 30;
            }
            default -> {
                return 31;
            }
        }
    }
}
