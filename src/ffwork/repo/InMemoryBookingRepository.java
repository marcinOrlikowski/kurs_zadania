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
    public void add(Booking b) {
        if (b == null) {
            throw new NullPointerException("Cannot add null reference");
        }
        findById(b.getId())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Object with such id already exists");
                });
        bookings.add(b);
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

    //todo
    @Override
    public List<Booking> findByResource(Resource r) {
        return bookings.stream()
                .filter(booking -> booking.getResource().equals(r))
                .toList();
    }

    //todo
    @Override
    public List<Booking> findByUser(User u) {
        return bookings.stream()
                .filter(booking -> booking.getUser().equals(u))
                .toList();
    }
}
