package ffwork.Payment;

import ffwork.money.Money;
//todo
public class WalletPayment extends Payment {
    public WalletPayment(Money amount, String paymentId) {
        super(amount, paymentId);
    }

    @Override
    void capture() throws IllegalStateException {

    }

    @Override
    void refund() throws IllegalStateException {

    }
}
