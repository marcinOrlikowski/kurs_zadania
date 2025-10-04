package ffwork.domain.resource;

import ffwork.money.Money;

import java.util.Objects;

public abstract class Resource {
    protected String name;
    protected Money customHourlyRate;

    public Resource(String name) {
        this.name = name.trim();
    }

    public Resource(String name, Money customHourlyRate) {
        this.name = name.trim();
        this.customHourlyRate = customHourlyRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(name, resource.name) && Objects.equals(customHourlyRate, resource.customHourlyRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, customHourlyRate);
    }

    @Override
    public String toString() {
        return String.format("Resource name: %s, price per hour: %s", name, hourlyRate());
    }
}
