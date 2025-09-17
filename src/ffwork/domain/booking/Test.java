package ffwork.domain.booking;

import ffwork.domain.resource.Room;
import ffwork.domain.user.User;
import ffwork.money.Money;
import ffwork.time.FFDateTime;

import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Booking booking = new Booking(new User("abc@df.com", "orlik"),
                new Room("room1", 20, Set.of("Whiteboard")),
                FFDateTime.of(2025, 9, 13, 14, 26),
                FFDateTime.of(2025, 9, 13, 15, 0),
                Money.of("50"),
                null);

        Booking booking2 = new Booking(new User("abcd@df.com", "orlik2"),
                new Room("room2", 20, Set.of("Whiteboard")),
                FFDateTime.of(2025, 9, 13, 14, 26),
                FFDateTime.of(2025, 9, 13, 15, 0),
                Money.of("100"),
                null);

        System.out.println(booking.getId());
        System.out.println(booking2.getId());
        booking2.changeStatus(BookingStatus.CONFIRMED);
        System.out.println(booking2.durationMinutes());
    }
}
