package ffwork.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money implements Comparable<Money> {
    private BigDecimal amount;
    private static final String CURRENCY = "PLN";
    private static final String MONEY_FORMAT = "\\d+(\\.\\d{1,2})?";

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private Money(BigDecimal amount) {
        if (amount != null) {
            isAmountPositive(amount);
            this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public static Money of(String amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Cannot provide null");
        }
        if (!amount.matches(MONEY_FORMAT)) {
            throw new IllegalArgumentException("Money value must be positive and in '123.45' format");
        }
        return new Money(new BigDecimal(amount.trim()));
    }

    public static Money of(double amount) {
        throw new IllegalArgumentException("Enter value in string format");
    }

    public Money add(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot add null");
        }
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot subtract null");
        }
        if (other.compareTo(this) > 0) {
            throw new IllegalArgumentException("Subtract value is bigger than current one");
        }
        return new Money(this.amount.subtract(other.amount));
    }

    public Money multiply(BigDecimal m) {
        if (m == null) {
            throw new IllegalArgumentException("Cannot multiply by null");
        }
        return new Money(this.amount.multiply(m));
    }

    public Money multiply(double m) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(m)));
    }

    public Money divide(double m) {
        if (m == 0) {
            throw new IllegalArgumentException("Cannot divide by 0");
        }
        return new Money(this.amount.divide(BigDecimal.valueOf(m), 2, RoundingMode.HALF_UP));
    }

    private void isAmountPositive(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Cannot provide null");
        }
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
