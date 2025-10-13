package ffwork.repo;

import ffwork.domain.booking.Booking;
import ffwork.domain.resource.Resource;
import ffwork.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookingRepository implements BookingRepository {
    List<Booking> bookings = new ArrayList<>();

    @Override
    public void add(Booking booking) {
        validateNull(booking);
        validateIfBookingAlreadyExists(booking);
        bookings.add(booking);
    }

    private void validateIfBookingAlreadyExists(Booking booking) {
        findById(booking.getId())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Object with such id already exists");
                });
    }

    private static void validateNull(Booking booking) {
        if (booking == null) {
            throw new NullPointerException("Cannot add null reference");
        }
    }

    @Override
    public Optional<Booking> findById(String id) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(id)) {
                return Optional.of(booking);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Booking> findAll() {
        return bookings;
    }

    @Override
    public List<Booking> findByResource(Resource resource) {
        return bookings.stream()
                .filter(booking -> booking.getResource().equals(resource))
                .toList();
    }

    @Override
    public List<Booking> findByUser(User user) {
        return bookings.stream()
                .filter(booking -> booking.getUser().equals(user))
                .toList();
    }
}
