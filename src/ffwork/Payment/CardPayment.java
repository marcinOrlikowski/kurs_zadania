package ffwork.Payment;

import ffwork.money.Money;
//todo
public class CardPayment extends Payment {
    String last4;

    public CardPayment(Money amount, String paymentId, String last4) {
        super(amount, paymentId);
        this.last4 = last4;
    }

    @Override
    void capture() throws IllegalStateException {

    }

    @Override
    void refund() throws IllegalStateException {

    }
}
