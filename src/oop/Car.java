package oop;

public class Car {
    String brand;
    int yearOfProduction;

    public Car() {
        this.brand = "Unknown";
        this.yearOfProduction = 2000;
    }

    public Car(String brand, int yearOfProduction) {
        this.brand = brand;
        this.yearOfProduction = yearOfProduction;
    }

    public void showDifference(){
        String brand = "local Brand";
        System.out.println(brand);
        System.out.println(this.brand);
    }

    @Override
    public String toString() {
        return "Brand: " + brand + ", year of production: " + yearOfProduction;
    }
}
