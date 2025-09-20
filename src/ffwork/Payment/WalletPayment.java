package ffwork.Payment;

import ffwork.domain.booking.Booking;
import ffwork.money.Money;

public class WalletPayment extends Payment {
    public WalletPayment(Money amount, String paymentId) {
        super(amount, paymentId);
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
