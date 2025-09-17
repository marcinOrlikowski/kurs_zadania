package ffwork.service;

import ffwork.discount.NoDiscount;
import ffwork.domain.booking.BookingStatus;
import ffwork.domain.resource.Device;
import ffwork.domain.user.User;
import ffwork.pricing.HappyHoursPricing;
import ffwork.repo.InMemoryBookingRepository;
import ffwork.repo.InMemoryResourceRepository;
import ffwork.repo.InMemoryUserRepository;
import ffwork.time.FFDateTime;

public class Test {
    public static void main(String[] args) {
        BookingService bookingService = new BookingService(new InMemoryUserRepository(),
                new InMemoryResourceRepository(),
                new InMemoryBookingRepository(),
                new HappyHoursPricing(),
                new NoDiscount());
//        bookingService.book(new User("anna@ex.com", "Anna Nowak"),
//                new Room("Sala Alfa", Money.of("12"),80),
//                FFDateTime.of(2025,9,15,10,00),
//                FFDateTime.of(2025,9,15,12,00)
//        );
//        bookingService.book(new User("anna@ex.com", "Anna Nowak"),
//                new Room("Sala Alfa", Money.of("12"),80),
//                FFDateTime.of(2025,9,15,10,00),
//                FFDateTime.of(2025,9,15,13,00)
//        );

        Device projektor = new Device("Projektor-1", 3);

        System.out.println(bookingService.book(new User("anna@ex.com", "Anna Nowak"),
                projektor,
                FFDateTime.of(2025, 9, 15, 13, 00),
                FFDateTime.of(2025, 9, 15, 15, 00)
        ).getId());
        System.out.println(bookingService.book(new User("anna@ex.com", "Anna Nowak"),
                projektor,
                FFDateTime.of(2025, 9, 15, 13, 00),
                FFDateTime.of(2025, 9, 15, 15, 00)
        ).getCalculatedPrice());
        System.out.println(bookingService.book(new User("anna@ex.com", "Anna Nowak"),
                projektor,
                FFDateTime.of(2025, 9, 15, 14, 00),
                120
        ).getCalculatedPrice());

        bookingService.confirm("BK-<20250915>-<1>");
        bookingService.complete("BK-<20250915>-<1>");

        System.out.println("By user");
        bookingService.list(new User("anna@ex.com", "Anna Nowak"));
        System.out.println("By resource");
        bookingService.list(projektor);
        System.out.println("By status");
        bookingService.list(BookingStatus.PENDING);

    }
}
