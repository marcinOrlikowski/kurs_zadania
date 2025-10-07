package ffwork.service;

import ffwork.discount.Discountable;
import ffwork.domain.booking.Booking;
import ffwork.domain.booking.BookingStatus;
import ffwork.domain.resource.Device;
import ffwork.domain.resource.Resource;
import ffwork.domain.user.User;
import ffwork.money.Money;
import ffwork.pricing.PricingPolicy;
import ffwork.repo.InMemoryBookingRepository;
import ffwork.repo.InMemoryResourceRepository;
import ffwork.repo.InMemoryUserRepository;
import ffwork.time.FFDateTime;

import java.util.List;

public class BookingService {
    private InMemoryUserRepository inMemoryUserRepository;
    private InMemoryResourceRepository inMemoryResourceRepository;
    private InMemoryBookingRepository inMemoryBookingRepository;
    private PricingPolicy pricingPolicy;
    private Discountable discount;

    public BookingService(InMemoryUserRepository inMemoryUserRepository, InMemoryResourceRepository inMemoryResourceRepository,
                          InMemoryBookingRepository inMemoryBookingRepository, PricingPolicy pricingPolicy, Discountable discount) {
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.inMemoryResourceRepository = inMemoryResourceRepository;
        this.inMemoryBookingRepository = inMemoryBookingRepository;
        this.pricingPolicy = pricingPolicy;
        this.discount = discount;
    }

    public Booking book(User user, Resource resource, FFDateTime start, FFDateTime end) {
        overlaps(resource, start, end);
        Booking booking = new Booking(user, resource, start, end);
        Money base = pricingPolicy.price(booking);
        Money finalPrice = discount.applyDiscount(base, user);
        booking.setCalculatedPrice(finalPrice);
        inMemoryBookingRepository.add(booking);
        return booking;
    }

    public Booking book(User u, Resource r, FFDateTime start, int durationMinutes) {
        FFDateTime end = start.plusMinutes(durationMinutes);
        return book(u, r, start, end);
    }

    public void setPricingPolicy(PricingPolicy pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
    }

    public void setDiscount(Discountable discount) {
        this.discount = discount;
    }

    public void confirm(String bookingId) {
        Booking booking = inMemoryBookingRepository.findById(bookingId).
                orElseThrow(() -> new IllegalArgumentException("There is no booking with this id"));
        booking.changeStatus(BookingStatus.CONFIRMED);
    }

    public void cancel(String bookingId) {
        Booking booking = inMemoryBookingRepository.findById(bookingId).
                orElseThrow(() -> new IllegalArgumentException("There is no booking with this id"));
        booking.getPayment().refund();
        booking.changeStatus(BookingStatus.CANCELLED);
    }

    public void complete(String bookingId) {
        Booking booking = inMemoryBookingRepository.findById(bookingId).
                orElseThrow(() -> new IllegalArgumentException("There is no booking with this id"));
        booking.changeStatus(BookingStatus.COMPLETED);
    }

    public void list(User user) {
        List<Booking> byUser = inMemoryBookingRepository.findByUser(user);
        byUser.forEach(System.out::println);
    }

    public void list(Resource resource) {
        List<Booking> byUser = inMemoryBookingRepository.findByResource(resource);
        byUser.forEach(System.out::println);
    }

    public void list(BookingStatus status) {
        inMemoryBookingRepository.findAll().stream()
                .filter(booking -> booking.getStatus() == status)
                .toList()
                .forEach(System.out::println);
    }

    private void overlaps(Resource resource, FFDateTime start, FFDateTime end) {
        List<Booking> byResource = inMemoryBookingRepository.findByResource(resource);
        List<Booking> currentListOfBookings = byResource.stream()
                .filter(booking -> booking.getStatus() == BookingStatus.CONFIRMED || booking.getStatus() == BookingStatus.PENDING)
                .toList();
        if (resource instanceof Device) {
            int numberOfOverlaping = 0;
            for (Booking booking : currentListOfBookings) {
                if (booking.getStart().toEpochMinutes() < end.toEpochMinutes() && start.toEpochMinutes() < booking.getEnd().toEpochMinutes()) {
                    numberOfOverlaping++;
                }
            }
            if (numberOfOverlaping >= ((Device) resource).getQuantity()) {
                throw new IllegalArgumentException("Resource not available");
            }
        } else {
            for (Booking booking : currentListOfBookings) {
                if (booking.getStart().toEpochMinutes() < end.toEpochMinutes() && start.toEpochMinutes() < booking.getEnd().toEpochMinutes()) {
                    throw new IllegalArgumentException("Resource not available");
                }
            }
        }
    }
}
