package ffwork.pricing;

import ffwork.domain.booking.Booking;
import ffwork.money.Money;

import java.math.BigDecimal;

public class HappyHoursPricing implements PricingPolicy {
    @Override
    public Money price(Booking booking) {
        int startingHour = booking.getStart().hour;
        if (startingHour >= 14 && startingHour <= 15) {
            return new StandardPricing().price(booking).multiply(0.7);
        } else {
            return new StandardPricing().price(booking);
        }
    }
}
