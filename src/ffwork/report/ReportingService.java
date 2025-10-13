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
        int minutesInRange = from.minutesUntil(to);
        Map<Resource, Double> utilization = new HashMap<>();
        List<Resource> allResources = inMemoryResourceRepository.findAll();
        for (Resource resource : allResources) {
            double rounded = getRoundedUtilizationValue(from, to, resource, minutesInRange);
            utilization.put(resource, rounded);
        }
        return utilization;
    }

    public Map<String, Money> revenueByResource(FFDateTime from, FFDateTime to) {
        Map<String, Money> revenues = new HashMap<>();
        List<Resource> allResources = inMemoryResourceRepository.findAll();
        for (Resource resource : allResources) {
            List<Booking> bookings = getBookingsInRange(from, to, resource);
            Money totalMoney = getTotalMoney(bookings);
            revenues.put(resource.getName(), totalMoney);
        }
        return revenues;
    }

    public Money totalRevenue() {
        List<Booking> bookings = inMemoryBookingRepository.findAll();
        return getTotalMoney(bookings);
    }

    private double getRoundedUtilizationValue(FFDateTime from, FFDateTime to, Resource resource, int minutesInRange) {
        List<Booking> bookingsInRange = getBookingsInRange(from, to, resource);
        int totalReservedMinutes = getTotalReservedMinutes(bookingsInRange);
        double percentage = 100.0 * totalReservedMinutes / minutesInRange;
        BigDecimal bd = BigDecimal.valueOf(percentage);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static int getTotalReservedMinutes(List<Booking> bookings) {
        int totalReservedMinutes = 0;
        for (Booking booking : bookings) {
            int duration = booking.durationMinutes();
            totalReservedMinutes += duration;
        }
        return totalReservedMinutes;
    }

    private List<Booking> getBookingsInRange(FFDateTime from, FFDateTime to, Resource resource) {
        return inMemoryBookingRepository.findByResource(resource).stream()
                .filter(ReportingService::isConfirmedOrCompleted)
                .filter(booking -> isBookingInRange(from, to, booking))
                .toList();
    }

    private static boolean isBookingInRange(FFDateTime from, FFDateTime to, Booking booking) {
        return booking.getStart().toEpochMinutes() >= from.toEpochMinutes() && booking.getEnd().toEpochMinutes() <= to.toEpochMinutes();
    }

    private static boolean isConfirmedOrCompleted(Booking booking) {
        return booking.getStatus() == BookingStatus.CONFIRMED || booking.getStatus() == BookingStatus.COMPLETED;
    }

    private static Money getTotalMoney(List<Booking> bookings) {
        Money totalMoney = Money.ZERO;
        for (Booking booking : bookings) {
            Money amount = getPaymentAmount(booking);
            totalMoney = totalMoney.add(amount);
        }
        return totalMoney;
    }

    private static Money getPaymentAmount(Booking booking) {
        Money amount = Money.ZERO;
        Payment payment = booking.getPayment();
        if (payment != null) {
            if (payment.getStatus() == PaymentStatus.CAPTURED) {
                amount = payment.getAmount();
            }
        }
        return amount;
    }
}
