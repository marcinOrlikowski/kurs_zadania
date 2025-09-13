package ffwork.repo;

import ffwork.domain.booking.Booking;
import ffwork.domain.booking.BookingStatus;
import ffwork.domain.resource.Desk;
import ffwork.domain.resource.Device;
import ffwork.domain.resource.Resource;
import ffwork.domain.resource.Room;
import ffwork.domain.user.User;
import ffwork.money.Money;
import ffwork.time.FFDateTime;

import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        //InMemoryUserRepository
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
        User user = new User("abc@df.com", "Orlik");
        User user2 = new User("abcd@df.com", "Orlik");
        inMemoryUserRepository.add(user);
        inMemoryUserRepository.add(user2);
        List<User> all = inMemoryUserRepository.findAll();
        all.forEach(System.out::println);

        //InMemoryResourceRepository
        InMemoryResourceRepository inMemoryResourceRepository = new InMemoryResourceRepository();
        Resource device = new Device("device", 50);
        Resource device2 = new Device("device2", 50);
        Resource desk = new Desk("desk", Desk.DeskType.HOT);
        inMemoryResourceRepository.add(device);
        inMemoryResourceRepository.add(device2);
        inMemoryResourceRepository.add(desk);

        List<Resource> byType = inMemoryResourceRepository.findByType(Device.class);
        for (Resource resource : byType) {
            System.out.println(resource.describe());
        }

        //InMemoryBookingRepository
        InMemoryBookingRepository inMemoryBookingRepository = new InMemoryBookingRepository();
        Booking booking = new Booking(new User("abc@df.com", "orlik"),
                new Room("room1", 20, Set.of("Whiteboard")),
                FFDateTime.of(2025, 9, 13, 14, 26),
                FFDateTime.of(2025, 9, 13, 15, 0),
                BookingStatus.PENDING,
                Money.of("50"),
                null);

        Booking booking2 = new Booking(new User("abc@df.com", "orlik2"),
                new Room("room2", 20, Set.of("Whiteboard")),
                FFDateTime.of(2025, 9, 13, 14, 26),
                FFDateTime.of(2025, 9, 13, 15, 0),
                BookingStatus.PENDING,
                Money.of("100"),
                null);

        inMemoryBookingRepository.add(booking);
        inMemoryBookingRepository.add(booking2);
    }
}
