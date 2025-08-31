package enum_;

import java.util.Comparator;

public enum Month {
    JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

    int days;

    Month(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    @Override
    public String toString() {
        return name() + " (days: " + this.getDays() + ")";
    }


}
