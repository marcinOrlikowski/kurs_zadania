package ffwork.domain.resource;

import ffwork.money.Money;

public abstract class Resource {
    String name;
    Money customHourlyRate;

    public Resource(String name) {
        this.name = name;
    }

    public Resource(String name, Money customHourlyRate) {
        this.name = name;
        this.customHourlyRate = customHourlyRate;
    }

    protected abstract Money baseRatePerHour();

    public abstract String describe();

    public Money hourlyRate() {
        if (customHourlyRate != null) {
            return customHourlyRate;
        } else {
            return baseRatePerHour();
        }
    }
}
