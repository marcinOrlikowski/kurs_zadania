package ffwork.Payment;

import ffwork.money.Money;

public class CardPayment extends Payment {
    String last4;

    public CardPayment(Money amount, String paymentId, String last4) {
        super(amount, paymentId);
        this.last4 = last4;
    }

    @Override
    public void capture() throws IllegalStateException {
        if (this.status != PaymentStatus.INITIATED) {
            throw new IllegalStateException(this.status + " status cannot be captured");
        }
        this.status = PaymentStatus.CAPTURED;
    }

    @Override
    public void refund() throws IllegalStateException {
        if (this.status != PaymentStatus.CAPTURED) {
            throw new IllegalStateException("Only CAPTURED payments can be refunded");
        }
        this.status = PaymentStatus.REFUNDED;
    }


}
