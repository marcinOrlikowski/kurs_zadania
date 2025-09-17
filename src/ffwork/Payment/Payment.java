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

    abstract void capture() throws IllegalStateException;

    abstract void refund() throws IllegalStateException;


}
