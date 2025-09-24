package exceptions;

public class PasswordValidator {
    public static void validate(String pwd) {
        if (pwd == null || pwd.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        if (!pwd.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least 1 digit");
        }
    }
}
