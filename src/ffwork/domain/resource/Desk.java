package ffwork.domain.resource;

import ffwork.money.Money;

public class Desk extends Resource {
    private DeskType type;

    public Desk(String name, DeskType type) {
        super(name);
        this.type = type;
    }

    public Desk(String name, Money customHourlyRate, DeskType type) {
        super(name, customHourlyRate);
        this.type = type;
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

