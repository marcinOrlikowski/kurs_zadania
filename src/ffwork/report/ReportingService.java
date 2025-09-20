package ffwork.report;

import ffwork.Payment.Payment;
import ffwork.Payment.PaymentStatus;
import ffwork.domain.booking.Booking;
import ffwork.domain.booking.BookingStatus;
import ffwork.domain.resource.Resource;
import ffwork.money.Money;
import ffwork.repo.InMemoryBookingRepository;
import ffwork.repo.InMemoryResourceRepository;
import ffwork.time.FFDateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportingService {
    private InMemoryBookingRepository inMemoryBookingRepository;
    private InMemoryResourceRepository inMemoryResourceRepository;

    public ReportingService(InMemoryBookingRepository inMemoryBookingRepository, InMemoryResourceRepository inMemoryResourceRepository) {
        this.inMemoryBookingRepository = inMemoryBookingRepository;
        this.inMemoryResourceRepository = inMemoryResourceRepository;
    }

    public Map<Resource, Double> utilization(FFDateTime from, FFDateTime to) {
        Map<Resource, Double> utilization = new HashMap<>();
        List<Resource> allResources = inMemoryResourceRepository.findAll();
        for (Resource resource : allResources) {
            int totalReservedminuted = 0;
            int minutesInRange = from.minutesUntil(to);
            List<Booking> bookings = inMemoryBookingRepository.findByResource(resource).stream()
                    .filter(booking -> booking.getStatus() == BookingStatus.CONFIRMED || booking.getStatus() == BookingStatus.COMPLETED)
                    .filter(booking -> booking.getStart().toEpochMinutes() >= from.toEpochMinutes() && booking.getEnd().toEpochMinutes() <= to.toEpochMinutes())
                    .toList();

            for (Booking booking : bookings) {
                int duration = booking.durationMinutes();
                totalReservedminuted += duration;
            }
            double percentage = 100.0 * totalReservedminuted / minutesInRange;
            BigDecimal bd = BigDecimal.valueOf(percentage);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double rounded = bd.doubleValue();
            utilization.put(resource, rounded);
        }
        return utilization;
    }

    Map<String, Money> revenueByResource(FFDateTime from, FFDateTime to) {
        Map<String, Money> revenues = new HashMap<>();
        List<Resource> allResources = inMemoryResourceRepository.findAll();
        for (Resource resource : allResources) {
            Money totalMoney = Money.ZERO;
            List<Booking> bookings = inMemoryBookingRepository.findByResource(resource).stream()
                    .filter(booking -> booking.getStatus() == BookingStatus.CONFIRMED || booking.getStatus() == BookingStatus.COMPLETED)
                    .filter(booking -> booking.getStart().toEpochMinutes() >= from.toEpochMinutes() && booking.getEnd().toEpochMinutes() <= to.toEpochMinutes())
                    .toList();

            for (Booking booking : bookings) {
                Payment payment = booking.getPayment();
                if (payment != null) {
                    if (payment.getStatus() == PaymentStatus.CAPTURED) {
                        totalMoney = totalMoney.add(payment.getAmount());
                    }
                }
            }
            revenues.put(resource.getName(), totalMoney);
        }
        return revenues;
    }

    public Money totalRevenue() {
        Money totalRevenue = Money.ZERO;
        List<Booking> bookings = inMemoryBookingRepository.findAll();

        for (Booking booking : bookings) {
            Payment payment = booking.getPayment();
            if (payment != null) {
                if (payment.getStatus() == PaymentStatus.CAPTURED) {
                    totalRevenue = totalRevenue.add(payment.getAmount());
                }
            }
        }
        return totalRevenue;
    }

}
