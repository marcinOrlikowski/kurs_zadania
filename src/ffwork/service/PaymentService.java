package ffwork.service;

import ffwork.Payment.CardPayment;
import ffwork.Payment.Payment;
import ffwork.Payment.WalletPayment;
import ffwork.domain.booking.Booking;
import ffwork.money.Money;
import ffwork.repo.InMemoryBookingRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class PaymentService {
    private InMemoryBookingRepository inMemoryBookingRepository;

    public PaymentService(InMemoryBookingRepository inMemoryBookingRepository) {
        this.inMemoryBookingRepository = inMemoryBookingRepository;
    }

    public Payment payByCard(String bookingId, String last4) {
        Optional<Booking> byId = inMemoryBookingRepository.findById(bookingId);
        Booking booking = byId.orElseThrow(() -> new NoSuchElementException("There is no booking with this id"));
        String paymentId = createPaymentId(booking);
        Payment cardPayment = new CardPayment(booking.getCalculatedPrice(), paymentId, last4);
        cardPayment.capture();
        booking.setPayment(cardPayment);
        return cardPayment;
    }

    public Payment payByWallet(String bookingId) {
        Optional<Booking> byId = inMemoryBookingRepository.findById(bookingId);
        Booking booking = byId.orElseThrow(() -> new NoSuchElementException("There is no booking with this id"));
        String paymentId = createPaymentId(booking);
        Payment walletPayment = new WalletPayment(booking.getCalculatedPrice(), paymentId);
        sufficientFunds(booking);
        walletPayment.capture();
        booking.setPayment(walletPayment);
        return walletPayment;
    }

    public void refund(String bookingId) {
        Optional<Booking> byId = inMemoryBookingRepository.findById(bookingId);
        Booking booking = byId.orElseThrow(() -> new NoSuchElementException("There is no booking with this id"));
        Payment payment = booking.getPayment();
        if (payment == null) {
            throw new NoSuchElementException("There is no payment for this booking");
        }
        if (payment instanceof WalletPayment) {
            Money userBalance = booking.getUser().getWalletBalance();
            Money calculatedPrice = booking.getCalculatedPrice();
            booking.getUser().setWalletBalance(userBalance.add(calculatedPrice));
        }
        payment.refund();
    }

    private static String createPaymentId(Booking booking) {
        String paymentId = booking.getId().replaceAll("BK", "PAYMENT");
        return paymentId;
    }

    private static void sufficientFunds(Booking booking) {
        Money userBalance = booking.getUser().getWalletBalance();
        Money calculatedPrice = booking.getCalculatedPrice();
        if (userBalance.compareTo(calculatedPrice) < 0) {
            throw new IllegalArgumentException("User balance insufficient");
        }
        booking.getUser().setWalletBalance(userBalance.subtract(calculatedPrice));
    }
}
