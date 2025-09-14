package ffwork.domain.resource;

import ffwork.money.Money;

public class Device extends Resource {
    private int quantity;

    public Device(String name, int quantity) {
        super(name);
        this.quantity = quantity;
    }

    public Device(String name, Money customHourlyRate, int quantity) {
        super(name, customHourlyRate);
        this.quantity = quantity;
    }

    @Override
    protected Money baseRatePerHour() {
        return Money.of("100");
    }

    @Override
    public String describe() {
        return "Device name: " + name + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price per hour " + hourlyRate() + "\n";
    }
}
