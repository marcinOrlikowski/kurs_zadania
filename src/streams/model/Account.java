package streams.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private final AccountType type;
    private final String number;
    private final BigDecimal amount;
    private final Currency currency;

    public Account(AccountType type, String number, BigDecimal amount, Currency currency) {
        this.type = type;
        this.number = number;
        this.amount = amount;
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return type == account.type && Objects.equals(number, account.number) && Objects.equals(amount, account.amount) && currency == account.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number, amount, currency);
    }

    @Override
    public String toString() {
        return "Account{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
