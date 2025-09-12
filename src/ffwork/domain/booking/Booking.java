package ffwork.domain.booking;

import ffwork.Payment.Payment;
import ffwork.domain.resource.Resource;
import ffwork.domain.user.User;
import ffwork.money.Money;
import ffwork.time.FFDateTime;

public class Booking {
    String id;
    User user;
    Resource Resource;
    FFDateTime start;
    FFDateTime end;
    BookingStatus status;
    Money calculatedPrice;
    Payment payment;
}
