package oop;

public class Car {
    String brand;
    int yearOfProduction;

    public Car() {
        this.brand = "Unknown";
        this.yearOfProduction = 2000;
    }

    @Override
    public String toString() {
        return "Brand: " + brand + ", year of production: " + yearOfProduction;
    }
}
