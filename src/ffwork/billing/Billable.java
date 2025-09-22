package ffwork.billing;

import ffwork.domain.booking.Booking;

public interface Billable {
    Invoice toInvoice(Booking booking);
}
