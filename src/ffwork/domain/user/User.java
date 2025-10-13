package ffwork.domain.user;

import ffwork.money.Money;

import java.util.Objects;

public class User {
    private String email;
    private String taxId;
    private Money walletBalance = Money.of("1000");

    private final static String EMAIL_FORMAT = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final static int MIN_DISPLAY_NAME_LENGTH = 3;
    private final static int MAX_DISPLAY_NAME_LENGTH = 30;

    public User(String email, String displayName) {
        validateMail(email);
        validateDisplayName(displayName);
        this.email = email.trim();
        this.taxId = displayName.trim();
    }

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return taxId;
    }

    public Money getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Money walletBalance) {
        this.walletBalance = walletBalance;
    }

    private void validateMail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("email cannot be empty");
        }
        if (!email.trim().matches(EMAIL_FORMAT)) {
            throw new IllegalArgumentException("Invalid email format : should be in 'abc@df.gh'");
        }
    }

    private void validateDisplayName(String displayName) {
        if (displayName == null || displayName.trim().isEmpty()) {
            throw new IllegalArgumentException("Display Name cannot be empty");
        }
        if (displayName.trim().length() < MIN_DISPLAY_NAME_LENGTH || displayName.trim().length() > MAX_DISPLAY_NAME_LENGTH) {
            throw new IllegalArgumentException("Display Name need to have 3-30 characters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(taxId, user.taxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, taxId);
    }

    @Override
    public String toString() {
        return "email: " + email + ", display name: " + taxId;
    }
}
