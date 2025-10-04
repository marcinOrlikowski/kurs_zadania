package ffwork.domain.resource;

import ffwork.money.Money;

public class Desk extends Resource {
    private DeskType type;

    public Desk(String name, DeskType type) {
        super(name);
        validateDeskType(type);
        this.type = type;
    }

    public Desk(String name, Money customHourlyRate, DeskType type) {
        super(name, customHourlyRate);
        validateDeskType(type);
        this.type = type;
    }

    private void validateDeskType(DeskType type) {
        if (type == null) {
            throw new IllegalArgumentException("Desk type cannot be null");
        }
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of("5");
    }

    @Override
    public String describe() {
        return "Desk name: " + name + "\n" +
                "Desk type: " + type + "\n" +
                "Desk price per hour: " + hourlyRate() + "\n";
    }

    public enum DeskType {
        HOT, FIXED
    }
}

