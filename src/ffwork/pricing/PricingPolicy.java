package ffwork.pricing;

import ffwork.domain.booking.Booking;
import ffwork.money.Money;

public interface PricingPolicy {
    Money price(Booking booking);
}
