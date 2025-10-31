package time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FlightsReservation {
    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Map<String, String> IATA_TO_ZONE_ID = Map.of(
            "WAW", "Europe/Warsaw",
            "LHR", "Europe/London",
            "NYC", "America/New_York",
            "LAX", "America/Los_Angeles",
            "HND", "Asia/Tokyo",
            "CCU", "Asia/Kolkata",
            "SIN", "Asia/Singapore");

    private final List<Flight> flights = new ArrayList<>();

    public void createTestFlights() {
        flights.add(createFlight("1. Same zone",
                "WAW",
                "WAW",
                "2025-01-15 10:00:00",
                "2025-01-15 12:30:00"));

        flights.add(createFlight(
                "2. Same zone - Different day",
                "WAW",
                "WAW",
                "2025-01-15 23:00:00",
                "2025-01-16 01:30:00"
        ));

        flights.add(createFlight(
                "3. Inter-zone",
                "WAW",
                "NYC",
                "2025-01-15 10:00:00",
                "2025-01-15 14:00:00"
        ));
        //Not existing hour is automatically changed to first valid
        flights.add(createFlight(
                "4. Change to summer time",
                "WAW",
                "WAW",
                "2025-03-30 01:00:00",
                "2025-03-30 05:00:00"
        ));
        //Automatically Chooses earlier offset
        flights.add(createFlight(
                "5. Change to winter time",
                "WAW",
                "WAW",
                "2025-10-26 01:00:00",
                "2025-10-26 02:30:00"
        ));

        flights.add(createFlight(
                "6. Changing date line",
                "HND",
                "LAX",
                "2025-01-15 10:00:00",
                "2025-01-14 18:00:00"
        ));

//            throws DateTimeException: Invalid airport - cannot get zoneId
//            flights.add(createFlight(
//                    "7. Invalid time zone",
//                    "HND",
//                    "LAXx",
//                    "2025-01-15 10:00:00",
//                    "2025-01-14 18:00:00"
//            ));

//            throws IllegalArgumentException: Departure time cannot be after arrival time
//            flights.add(createFlight(
//                    "8. Arrival before departure",
//                    "WAW",
//                    "WAW",
//                    "2025-01-15 10:00:00",
//                    "2025-01-15 08:00:00"
//            ));

        flights.add(createFlight(
                "9. No DST in arrival place",
                "WAW",
                "CCU",
                "2025-01-15 10:00:00",
                "2025-01-15 19:00:00"
        ));

        flights.add(createFlight(
                "10. Long flight",
                "WAW",
                "SIN",
                "2025-01-15 06:00:00",
                "2025-01-16 06:00:00"
        ));

        flights.add(createFlight(
                "11. Both ways same day WAW - LON",
                "WAW",
                "LHR",
                "2025-01-15 08:00:00",
                "2025-01-15 12:00:00"
        ));

        flights.add(createFlight(
                "11. Both ways same day LON - WAW",
                "LHR",
                "WAW",
                "2025-01-15 16:00:00",
                "2025-01-15 20:00:00"
        ));
    }

    public Flight createFlight(String flightNumber, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime) {
        validateNulls(flightNumber, departureAirport, arrivalAirport, departureTime, arrivalTime);
        LocalDateTime departureLocal = LocalDateTime.parse(departureTime, CUSTOM_FORMATTER);
        LocalDateTime arrivalLocal = LocalDateTime.parse(arrivalTime, CUSTOM_FORMATTER);
        ZoneId departureZoneId = getZoneIdFromAirport(departureAirport);
        ZoneId arrivalZoneId = getZoneIdFromAirport(arrivalAirport);
        ZonedDateTime departureZoned = ZonedDateTime.of(departureLocal, departureZoneId);
        ZonedDateTime arrivalZoned = ZonedDateTime.of(arrivalLocal, arrivalZoneId);
        validateDates(departureZoned, arrivalZoned);
        return new Flight(flightNumber, departureAirport, arrivalAirport, departureZoned, arrivalZoned);
    }

    public void printFlightsDurations() {
        for (Flight flight : flights) {
            System.out.println("\n" + flight.getFlightNumber());
            System.out.println(format(flight.calculateFlightDuration()));
        }
    }

    private static void validateDates(ZonedDateTime departureZoned, ZonedDateTime arrivalZoned) {
        if (departureZoned.toInstant().isAfter(arrivalZoned.toInstant())) {
            throw new IllegalArgumentException("Departure time cannot be after arrival time");
        }
    }

    private static void validateNulls(String flightNumber, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime) {
        Objects.requireNonNull(flightNumber, "Flight number cannot be null");
        Objects.requireNonNull(departureAirport, "Departure airport cannot be null");
        Objects.requireNonNull(arrivalAirport, "Arrival airport cannot be null");
        Objects.requireNonNull(departureTime, "departure time cannot be null");
        Objects.requireNonNull(arrivalTime, "arrival time cannot be null");
    }

    private String format(Duration duration) {
        return duration.toHours() + "h " + duration.toMinutesPart() + "m";
    }

    private ZoneId getZoneIdFromAirport(String airport) {
        if (!IATA_TO_ZONE_ID.containsKey(airport)) {
            throw new DateTimeException("Invalid airport - cannot get zoneId");
        }
        return ZoneId.of(IATA_TO_ZONE_ID.get(airport));
    }
}
