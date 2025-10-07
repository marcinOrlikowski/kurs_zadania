package exceptions;

public class Main {
    public static void main(String[] args) {
        System.out.println("-- Task 1 --");
//        PasswordValidator.validate("short"); //IllegalArgumentException: Password must be at least 8 characters
//        PasswordValidator.validate("nodigits"); //IllegalArgumentException: Password must contain at least 1 digit
        PasswordValidator.validate("password1"); //valid

        System.out.println("-- Task 2 --");
        System.out.println(NumberParser.parsePositiveInt("5"));

        System.out.println("-- Task 3 --");
        BankAccount bankAccount = new BankAccount(500);
        try {
            bankAccount.withdraw(1000);
        } catch (InsufficientFundsException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("End of transaction");
        }

        System.out.println("-- Task 4 --");
        SuppressedShowcase.run();

        System.out.println("-- Task 5 --");
        CommandProcessor.demo();
    }
}
