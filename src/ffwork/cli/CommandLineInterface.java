package ffwork.cli;

import ffwork.billing.Invoice;
import ffwork.discount.CompanyTierDiscount;
import ffwork.discount.NoDiscount;
import ffwork.discount.StudentDiscount;
import ffwork.domain.booking.Booking;
import ffwork.domain.resource.Desk;
import ffwork.domain.resource.Device;
import ffwork.domain.resource.Resource;
import ffwork.domain.resource.Room;
import ffwork.domain.user.CompanyUser;
import ffwork.domain.user.IndividualUser;
import ffwork.domain.user.User;
import ffwork.exceptions.InvalidCommandArgumentException;
import ffwork.money.Money;
import ffwork.pricing.HappyHoursPricing;
import ffwork.pricing.StandardPricing;
import ffwork.repo.InMemoryBookingRepository;
import ffwork.repo.InMemoryResourceRepository;
import ffwork.repo.InMemoryUserRepository;
import ffwork.report.ReportingService;
import ffwork.service.BillingService;
import ffwork.service.BookingService;
import ffwork.service.PaymentService;
import ffwork.time.FFDateTime;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineInterface {
    //Repositories
    private InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
    private InMemoryResourceRepository inMemoryResourceRepository = new InMemoryResourceRepository();
    private InMemoryBookingRepository inMemoryBookingRepository = new InMemoryBookingRepository();
    //Pricing
    private StandardPricing standardPricing = new StandardPricing();
    private HappyHoursPricing happyHoursPricing = new HappyHoursPricing();
    //Discounts
    private NoDiscount noDiscount = new NoDiscount();
    private StudentDiscount studentDiscount = new StudentDiscount();
    private CompanyTierDiscount companyTierDiscount = new CompanyTierDiscount();
    //services
    private BookingService bookingService = new BookingService(inMemoryUserRepository, inMemoryResourceRepository, inMemoryBookingRepository, standardPricing, noDiscount);
    private ReportingService reportingService = new ReportingService(inMemoryBookingRepository, inMemoryResourceRepository);
    private PaymentService paymentService = new PaymentService(inMemoryBookingRepository);
    private BillingService billingService = new BillingService(inMemoryBookingRepository);
    //input
    Scanner sc = new Scanner(System.in);

    public void run() {
        printMenu();
        boolean exit = false;
        while (!exit) {
            try {
                Command command;
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    continue;
                }
                List<String> tokens = getTokens(input);
                try {
                    command = Command.valueOf(tokens.getFirst().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: Command not recognized, type HELP to see all commands");
                    continue;
                }

                switch (command) {
                    case ADD_USER -> {
                        addUser(tokens);
                    }
                    case LIST_USERS -> {
                        listUsers();
                    }
                    case ADD_ROOM -> {
                        addRoom(tokens);
                    }
                    case ADD_DESK -> {
                        addDesk(tokens);
                    }
                    case ADD_DEVICE -> {
                        addDevice(tokens);
                    }
                    case LIST_RESOURCES -> {
                        listResources();
                    }
                    case BOOK -> {
                        book(tokens);
                    }
                    case CONFIRM -> {
                        confirm(input);
                    }
                    case CANCEL -> {
                        cancel(input);
                    }
                    case LIST_BOOKINGS -> {
                        listBookings();
                    }
                    case SET_PRICING -> {
                        setPricing(tokens);
                    }
                    case SET_DISCOUNT -> {
                        setDiscount(tokens);
                    }
                    case PAY -> {
                        pay(input);
                    }
                    case INVOICE -> {
                        invoice(input);
                    }
                    case REPORT -> {
                        report(tokens);
                    }
                    case HELP -> {
                        help();
                    }
                    case QUIT -> {
                        System.out.println("Good bye!");
                        exit = true;
                        sc.close();
                    }
                }
            } catch (IllegalArgumentException | InvalidCommandArgumentException | NoSuchElementException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void report(List<String> tokens) {
        if (tokens.size() != 4) {
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.REPORT));
        }
        String reportType = tokens.get(1);
        FFDateTime from = FFDateTime.parse(tokens.get(2));
        FFDateTime to = FFDateTime.parse(tokens.get(3));
        if (reportType.equalsIgnoreCase("UTILIZATION")) {
            Map<Resource, Double> utilization = reportingService.utilization(from, to);
            System.out.println("OK: Printing utilization report:");
            for (Map.Entry<Resource, Double> entry : utilization.entrySet()) {
                System.out.printf("Resource: %s, utilization percentage: %.2f%%%n", entry.getKey().getName(), entry.getValue());
            }
        } else if (reportType.equalsIgnoreCase("REVENUE")) {
            Money totalRevenue = reportingService.totalRevenue();
            Map<String, Money> revenue = reportingService.revenueByResource(from, to);
            System.out.println("OK: Printing revenue report:");
            System.out.printf("Total revenue: %s \n", totalRevenue);
            for (Map.Entry<String, Money> entry : revenue.entrySet()) {
                System.out.printf("Resource name: %s , revenue: %s%n", entry.getKey(), entry.getValue());
            }
        }
    }

    private void invoice(String input) {
        String bookingId = getBookingId(input);
        Booking booking = inMemoryBookingRepository.findById(bookingId)
                .orElseThrow(() -> new NoSuchElementException("ERROR: No booking with this id"));
        Invoice invoice = billingService.toInvoice(booking);
        System.out.println(invoice);
    }

    private void pay(String input) {
        String bookingId = getBookingId(input);
        List<String> tokens = getTokens(input);
        String paymentType = tokens.get(2);
        if (paymentType.equalsIgnoreCase("CARD") && tokens.size() == 4) {
            String last4 = tokens.get(3);
            paymentService.payByCard(bookingId, last4);
            System.out.println("OK: Successfully paid by CARD");
        } else if (paymentType.equalsIgnoreCase("WALLET") && tokens.size() == 3) {
            paymentService.payByWallet(bookingId);
            System.out.println("OK: Successfully paid by WALLET");
        } else
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.PAY));

    }

    private void setDiscount(List<String> tokens) {
        String discount = tokens.get(1);
        if (discount.equalsIgnoreCase("NONE")) {
            bookingService.setDiscount(noDiscount);
            System.out.println("OK: Changed discount to NONE");
        } else if (discount.equalsIgnoreCase("STUDENT")) {
            bookingService.setDiscount(studentDiscount);
            System.out.println("OK: Changed discount to STUDENT");
        } else if (discount.equalsIgnoreCase("COMPANY_TIER")) {
            bookingService.setDiscount(companyTierDiscount);
            System.out.println("OK: Changed discount to COMPANY_TIER");
        } else
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.SET_DISCOUNT));
    }

    private void setPricing(List<String> tokens) {
        String pricingPolicy = tokens.get(1);
        if (pricingPolicy.equalsIgnoreCase("STANDARD")) {
            bookingService.setPricingPolicy(standardPricing);
            System.out.println("OK: Changed pricing policy to STANDARD");
        } else if (pricingPolicy.equalsIgnoreCase("HAPPY_HOURS")) {
            bookingService.setPricingPolicy(happyHoursPricing);
            System.out.println("OK: Changed pricing policy to HAPPY_HOURS");
        } else
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.SET_PRICING));
    }

    private void listBookings() {
        List<Booking> bookings = inMemoryBookingRepository.findAll();
        if (bookings.isEmpty()) {
            System.err.println("ERROR: Bookings list is empty");
        } else {
            System.out.println("OK: Listing Bookings...");
            bookings.forEach(System.out::println);
        }
    }


    private void cancel(String input) {
        String bookingId = getBookingId(input);
        bookingService.cancel(bookingId);
    }

    private void confirm(String input) {
        String bookingId = getBookingId(input);
        bookingService.confirm(bookingId);
    }

    private void book(List<String> tokens) {
        if (tokens.size() != 5) {
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.BOOK));
        }
        User user = inMemoryUserRepository.findByEmail(tokens.get(1)).orElseThrow();
        Resource resource = inMemoryResourceRepository.findByName(tokens.get(2)).orElseThrow();
        FFDateTime start = FFDateTime.parse(tokens.get(3));
        int durationMinutes;
        if (tokens.get(4).matches(FFDateTime.DATE_TIME_FORMAT)) {
            FFDateTime end = FFDateTime.parse(tokens.get(4));
            bookingService.book(user, resource, start, end);
        } else {
            durationMinutes = Integer.parseInt(tokens.get(4));
            bookingService.book(user, resource, start, durationMinutes);
        }
        System.out.println("OK: Booking successfully added");
    }

    private void listResources() {
        List<Resource> resources = inMemoryResourceRepository.findAll();
        if (resources.isEmpty()) {
            System.err.println("ERROR: Resources list is empty");
        } else {
            System.out.println("OK: Listing Resources...");
            resources.forEach(System.out::println);
        }
    }

    private void addDevice(List<String> tokens) {
        Device device;
        if (tokens.size() == 4) {
            String name = tokens.get(1);
            int quantity = Integer.parseInt(tokens.get(2));
            Money customHourlyRate = Money.of(tokens.get(3));
            device = new Device(name, customHourlyRate, quantity);
        } else if (tokens.size() == 3) {
            String name = tokens.get(1);
            int quantity = Integer.parseInt(tokens.get(2));
            device = new Device(name, quantity);
        } else
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.ADD_DESK));
        inMemoryResourceRepository.add(device);
        System.out.printf("OK: Device %s successfully added %n", device.getName());
    }

    private void addDesk(List<String> tokens) {
        Desk desk;
        if (tokens.size() == 4) {
            String name = tokens.get(1);
            Desk.DeskType type = Desk.DeskType.valueOf(tokens.get(2).toUpperCase());
            Money customHourlyRate = Money.of(tokens.get(3));
            desk = new Desk(name, customHourlyRate, type);
        } else if (tokens.size() == 3) {
            String name = tokens.get(1);
            Desk.DeskType type = Desk.DeskType.valueOf(tokens.get(2).toUpperCase());
            desk = new Desk(name, type);
        } else
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.ADD_DESK));
        inMemoryResourceRepository.add(desk);
        System.out.printf("OK: Desk %s successfully added %n", desk.getName());
    }

    private void addRoom(List<String> tokens) {
        Room room;
        if (tokens.size() == 3) {
            String name = tokens.get(1);
            int seats = Integer.parseInt(tokens.get(2));
            room = new Room(name, seats);
        } else if (tokens.size() == 4) {
            String name = tokens.get(1);
            int seats = Integer.parseInt(tokens.get(2));
            Money customHourlyRate = Money.of(tokens.get(3));
            room = new Room(name, customHourlyRate, seats);
        } else
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.ADD_ROOM));
        inMemoryResourceRepository.add(room);
        System.out.printf("OK: Room %s successfully added %n", room.getName());
    }

    private void listUsers() {
        List<User> users = inMemoryUserRepository.findAll();
        if (users.isEmpty()) {
            System.err.println("ERROR: Users list is empty");
        } else {
            System.out.println("OK: Listing Users...");
            users.forEach(System.out::println);
        }
    }

    private void addUser(List<String> tokens) {
        User user;
        if (tokens.get(1).equalsIgnoreCase("INDIVIDUAL") && tokens.size() == 4) {
            String email = tokens.get(2);
            String fullName = tokens.get(3);
            user = new IndividualUser(email, fullName);
        } else if (tokens.get(1).equalsIgnoreCase("COMPANY") && tokens.size() == 5) {
            String email = tokens.get(2);
            String companyName = tokens.get(3);
            String nip = tokens.get(4);
            user = new CompanyUser(email, companyName, nip);
        } else
            throw new InvalidCommandArgumentException(invalidCommandMassage(Command.ADD_USER));
        inMemoryUserRepository.add(user);
        System.out.printf("OK: User %s successfully added %n", user.getDisplayName());
    }

    private void help() {
        System.out.println("Available commands:");
        for (Command command : Command.values()) {
            System.out.println(command.getDescription());
        }
    }

    private void printMenu() {
        System.out.println("Welcome!");
        System.out.println("Type 'HELP' to see available commands or 'QUIT' to exit program");
    }

    private String invalidCommandMassage(Command command) {
        return "ERROR: Invalid format : " + command.getDescription();
    }

    private List<String> getTokens(String input) {
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("(<[^>]+>)|(\"[^\"]+\")|(\\S+)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            tokens.add(matcher.group().replaceAll("[<>'\"]", ""));
        }
        return tokens;
    }

    private String getBookingId(String input) {
        Pattern pattern = Pattern.compile("(<[^>]+>)|(\"[^\"]+\")|(\\S+)");
        Matcher matcher = pattern.matcher(input);
        matcher.find(); // skipping first tokken
        if (matcher.find()) {
            return matcher.group().replaceAll("['\"]", "");
        }
        throw new NoSuchElementException("ERROR: Could not create id from input");
    }
}
