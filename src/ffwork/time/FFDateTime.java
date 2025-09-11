package ffwork.time;

public final class FFDateTime implements Comparable<FFDateTime> {
    public final int year;
    public final int month;
    public final int day;
    public final int hour;
    public final int minute;

    public FFDateTime(int year, int month, int day, int hour, int minute) {
        isDateValid(year, month, day, hour, minute);
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public static FFDateTime parse(String iso) {
        if (!iso.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")) {
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

    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    private static int getDaysInMonth(int month, int year) {
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

    private void isDateValid(int year, int month, int day, int hour, int minute) {
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

    @Override
    public int compareTo(FFDateTime o) {
        return 0;
    }

}
