package ffwork.service;

import ffwork.billing.Billable;
import ffwork.billing.Invoice;
import ffwork.domain.booking.Booking;
import ffwork.domain.user.User;
import ffwork.money.Money;
import ffwork.repo.InMemoryBookingRepository;
import ffwork.time.FFDateTime;

public class BillingService implements Billable {
    private InMemoryBookingRepository inMemoryBookingRepository;

    public BillingService(InMemoryBookingRepository inMemoryBookingRepository) {
        this.inMemoryBookingRepository = inMemoryBookingRepository;
    }

    @Override
    public Invoice toInvoice(Booking booking) {
        String invoiceId = booking.getId().replaceAll("BK", "INV");
        FFDateTime issueDate = booking.getStart();
        User buyer = booking.getUser();
        Money total = booking.getPayment().getAmount();
        String itemDescription = String.format("Reservation <%s> <%s-%s>", booking.getResource().getName(), booking.getStart().toString(), booking.getEnd().toString());
        return new Invoice(invoiceId, issueDate, buyer, total, itemDescription);
    }
}
