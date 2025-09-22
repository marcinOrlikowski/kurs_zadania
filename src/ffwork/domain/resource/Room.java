package ffwork.domain.resource;

import ffwork.money.Money;

import java.util.Set;

public class Room extends Resource {
    private int seats;
    private Set<String> equipment;

    public Room(String name, int seats) {
        super(name);
        this.seats = seats;
    }

    public Room(String name, int seats, Set<String> equipment) {
        super(name);
        this.seats = seats;
        this.equipment = equipment;
    }

    public Room(String name, Money customHourlyRate, int seats) {
        super(name, customHourlyRate);
        this.seats = seats;
    }

    public Room(String name, Money customHourlyRate, int seats, Set<String> equipment) {
        super(name, customHourlyRate);
        this.seats = seats;
        this.equipment = equipment;
    }


    @Override
    protected Money baseRatePerHour() {
        return Money.of("50");
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room name: ").append(name).append("\n");
        sb.append("Number of seats: ").append(seats).append("\n");
        sb.append("Room equipment: ");
        for (String e : equipment) {
            sb.append(e).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, "").append("\n");
        sb.append("Room price per hour: ").append(hourlyRate()).append("\n");
        return sb.toString();
    }
}
