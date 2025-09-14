package ffwork.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money implements Comparable<Money> {
    private BigDecimal amount;
    private static final String CURRENCY = "PLN";

    public Money(BigDecimal amount) {
        isAmountPositive(amount);
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public static Money of(String amount) {
        return new Money(new BigDecimal(amount));
    }

    public static Money of(double amount) {
        throw new IllegalArgumentException("Enter value in string format");
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    public Money multiply(BigDecimal m) {
        return new Money(this.amount.multiply(m));
    }

    public Money multiply(double m) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(m)));
    }

    public Money divide(double m) {
        return new Money(this.amount.divide(BigDecimal.valueOf(m),2,RoundingMode.HALF_UP));
    }

    private void isAmountPositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money cannot have negative value");
        }
    }

    @Override
    public String toString() {
        return amount + " " + CURRENCY;
    }

    @Override
    public int compareTo(Money o) {
        return this.amount.compareTo(o.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
