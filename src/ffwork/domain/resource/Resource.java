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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getCustomHourlyRate() {
        return customHourlyRate;
    }

    public void setCustomHourlyRate(Money customHourlyRate) {
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
