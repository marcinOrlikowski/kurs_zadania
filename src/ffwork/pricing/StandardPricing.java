package ffwork.pricing;

import ffwork.domain.booking.Booking;
import ffwork.money.Money;

import java.math.BigDecimal;

public class StandardPricing implements PricingPolicy {

    @Override
    public Money price(Booking booking) {
        int duration = booking.durationMinutes();
        Money hourlyRate = booking.getResource().hourlyRate();
        Money pricePerMinute = hourlyRate.divide(60);
        if (duration % 60 == 0) {
            int fullHours = duration / 60;
            return hourlyRate.multiply(new BigDecimal(fullHours));
        }
        return pricePerMinute.multiply(new BigDecimal(duration));
    }
}
