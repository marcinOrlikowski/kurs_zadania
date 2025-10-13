package ffwork.pricing;

import ffwork.domain.booking.Booking;
import ffwork.money.Money;

public class HappyHoursPricing implements PricingPolicy {
    private static final double DISCOUNT_MULTIPLAYER = 0.7;

    @Override
    public Money price(Booking booking) {
        int startingHour = booking.getStart().getHour();
        if (startingHour >= STARTING_HOUR && startingHour <= ENDING_HOUR) {
            return new StandardPricing().price(booking).multiply(DISCOUNT_MULTIPLAYER);
        } else {
            return new StandardPricing().price(booking);
        }
    }
}
