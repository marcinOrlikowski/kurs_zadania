package exceptions;

final class BankAccount {
    private int balance;

    public BankAccount(int initial) {
        this.balance = initial;
    }

    public void withdraw(int amount) throws InsufficientFundsException {
        // TODO: gdy amount > balance -> throw new InsufficientFundsException(...)
        if (this.balance < amount) {
            throw new InsufficientFundsException(String.format("Error : Insufficient funds : Required amount for transaction: %d, currect balance %d", amount, balance));
        }
        this.balance -= amount;
        System.out.printf("Successfully withdrawn %d, current balance is: %d%n", amount, balance);
        // w p.p. zmniejsz balance
    }
}
