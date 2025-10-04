package ffwork.domain.resource;

import ffwork.money.Money;

import java.util.Set;

public class Room extends Resource {
    private int seats;
    private Set<String> equipment;

    public Room(String name, int seats) {
        super(name);
        validateSeats(seats);
        this.seats = seats;
    }

    public Room(String name, int seats, Set<String> equipment) {
        this(name, seats);
        validateEquipment(equipment);
        this.equipment = equipment;
    }

    public Room(String name, Money customHourlyRate, int seats) {
        super(name, customHourlyRate);
        validateSeats(seats);
        this.seats = seats;
    }

    public Room(String name, Money customHourlyRate, int seats, Set<String> equipment) {
        super(name, customHourlyRate);
        validateSeats(seats);
        validateEquipment(equipment);
        this.seats = seats;
        this.equipment = equipment;
    }

    private void validateSeats(int seats) {
        if (seats < 0) {
            throw new IllegalArgumentException("Seats number cannot be negative");
        }
    }

    private void validateEquipment(Set<String> equipment) {
        if (equipment == null || equipment.isEmpty()) {
            throw new IllegalArgumentException("Equipment is empty");
        }
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
