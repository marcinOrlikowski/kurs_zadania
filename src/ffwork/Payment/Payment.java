package ffwork.Payment;

import ffwork.money.Money;

public abstract class Payment {
    Money amount;
    String paymentId;
    PaymentStatus status;

    public Payment(Money amount, String paymentId) {
        this.amount = amount;
        this.paymentId = paymentId;
        this.status = PaymentStatus.INITIATED;
    }

    abstract public void capture() throws IllegalStateException;

    abstract public void refund() throws IllegalStateException;

    public Money getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", paymentId='" + paymentId + '\'' +
                ", status=" + status +
                '}';
    }
}
