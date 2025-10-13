package ffwork.pricing;

import ffwork.domain.booking.Booking;
import ffwork.money.Money;
import ffwork.time.FFDateTime;

import java.math.BigDecimal;

public class StandardPricing implements PricingPolicy {
    @Override
    public Money price(Booking booking) {
        int duration = booking.durationMinutes();
        Money hourlyRate = booking.getResource().hourlyRate();
        Money pricePerMinute = hourlyRate.divide(FFDateTime.MINUTES_IN_HOUR);
        if (duration % FFDateTime.MINUTES_IN_HOUR == 0) {
            int fullHours = duration / FFDateTime.MINUTES_IN_HOUR;
            return hourlyRate.multiply(new BigDecimal(fullHours));
        }
        return pricePerMinute.multiply(new BigDecimal(duration));
    }
}
