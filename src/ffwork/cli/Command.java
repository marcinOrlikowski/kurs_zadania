package ffwork.cli;

public enum Command {
    ADD_USER("ADD_USER INDIVIDUAL <email> <fullName> || ADD_USER COMPANY <email> <companyName> <nip>"),
    LIST_USERS("LIST_USERS"),
    ADD_ROOM("ADD_ROOM <name> <seats> <hourlyRate>"),
    ADD_DESK("ADD_DESK <name> <hot|fixed> <hourlyRate>"),
    ADD_DEVICE("ADD_DEVICE <name> <quantity> <hourlyRate>"),
    LIST_RESOURCES("LIST_RESOURCES [TYPE=<ROOM|DESK|DEVICE>]"),
    BOOK("BOOK <userEmail> <resourceName> <startIso> <endIso> || BOOK <userEmail> <resourceName> <startIso> <durationMinutes>"),
    CONFIRM("CONFIRM <bookingId>"),
    CANCEL("CANCEL <bookingId>"),
    LIST_BOOKINGS("LIST_BOOKINGS [USER=<email>] [RESOURCE=<name>] [STATUS=<PENDING|CONFIRMED|CANCELLED|COMPLETED>]"),
    SET_PRICING("SET_PRICING STANDARD|HAPPY_HOURS"),
    SET_DISCOUNT("SET_DISCOUNT NONE|STUDENT|COMPANY_TIER"),
    PAY("PAY <bookingId> CARD <last4>` | `PAY <bookingId> WALLET"),
    INVOICE("INVOICE <bookingId>"),
    REPORT("REPORT UTILIZATION <fromIso> <toIso> || REPORT REVENUE <fromIso> <toIso>"),
    HELP("HELP"),
    QUIT("QUIT");

    private String description;

    Command(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
