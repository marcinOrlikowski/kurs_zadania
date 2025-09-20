package ffwork.report;

import ffwork.discount.NoDiscount;
import ffwork.domain.booking.Booking;
import ffwork.domain.resource.Device;
import ffwork.domain.resource.Resource;
import ffwork.domain.resource.Room;
import ffwork.domain.user.User;
import ffwork.money.Money;
import ffwork.pricing.HappyHoursPricing;
import ffwork.repo.InMemoryBookingRepository;
import ffwork.repo.InMemoryResourceRepository;
import ffwork.repo.InMemoryUserRepository;
import ffwork.service.BookingService;
import ffwork.service.PaymentService;
import ffwork.time.FFDateTime;

import java.util.Map;

public class test {
    public static void main(String[] args) {
        InMemoryBookingRepository inMemoryBookingRepository = new InMemoryBookingRepository();
        InMemoryResourceRepository inMemoryResourceRepository = new InMemoryResourceRepository();

        BookingService bookingService = new BookingService(new InMemoryUserRepository(),
                new InMemoryResourceRepository(),
                inMemoryBookingRepository,
                new HappyHoursPricing(),
                new NoDiscount());

        ReportingService reportingService = new ReportingService(inMemoryBookingRepository, inMemoryResourceRepository);
        PaymentService paymentService = new PaymentService(inMemoryBookingRepository);
        Room salaAlfa = new Room("Sala Alfa", Money.of("12"), 80);
        Device projektor = new Device("Projektor-1", 5);
        inMemoryResourceRepository.add(salaAlfa);
        inMemoryResourceRepository.add(projektor);

        Booking b1 = bookingService.book(new User("anna@ex.com", "Anna Nowak"),
                salaAlfa,
                FFDateTime.of(2025, 9, 15, 10, 0),
                FFDateTime.of(2025, 9, 15, 12, 0)
        );
        Booking b2 = bookingService.book(new User("anna@ex.com", "Anna Nowak"),
                salaAlfa,
                FFDateTime.of(2025, 9, 15, 12, 0),
                FFDateTime.of(2025, 9, 15, 14, 0)
        );
        Booking b3 = bookingService.book(new User("anna@ex.com", "Anna Nowak"),
                projektor,
                FFDateTime.of(2025, 9, 15, 12, 0),
                FFDateTime.of(2025, 9, 15, 14, 0)
        );

        bookingService.confirm(b1.getId());
        bookingService.confirm(b2.getId());
        bookingService.confirm(b3.getId());

        paymentService.payByWallet(b1.getId());
        paymentService.payByCard(b2.getId(),"1234");
        paymentService.payByCard(b3.getId(),"1234");
//        paymentService.refund(b1.getId());


        Map<Resource, Double> utilization = reportingService.utilization(FFDateTime.of(2025, 9, 15, 8, 0), FFDateTime.of(2025, 9, 15, 16, 0));

        Map<String, Money> revenues = reportingService.revenueByResource(FFDateTime.of(2025, 9, 15, 8, 0), FFDateTime.of(2025, 9, 15, 16, 0));

        System.out.println("Utilization:");
        for (Resource key : utilization.keySet()) {
            System.out.println("Key: " + key);
            System.out.println("Value: " + utilization.get(key));
        }
        System.out.println("Revenues:");
        for (String key : revenues.keySet()) {
            System.out.println("Key: " + key);
            System.out.println("Value: " + revenues.get(key));
        }


        Money totalRevenue = reportingService.totalRevenue();
        System.out.println("Total revenue: " + totalRevenue);

    }
}
