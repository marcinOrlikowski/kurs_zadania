package ffwork.pricing;

import ffwork.domain.booking.Booking;
import ffwork.money.Money;

public interface PricingPolicy {
    int STARTING_HOUR = 14;
    int ENDING_HOUR = 15;

    Money price(Booking booking);
}
