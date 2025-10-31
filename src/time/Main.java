package time;

public class Main {
    public static void main(String[] args) {
        FlightsReservation flightsReservation = new FlightsReservation();
        flightsReservation.createTestFlights();
        flightsReservation.printFlightsDurations();
    }
}
