package oop;

public class Car {
    String brand;
    int yearOfProduction;

    public Car(String brand, int yearOfProduction) {
        this.brand = brand;
        this.yearOfProduction = yearOfProduction;
    }

    public Car() {
        this("Unknown", 2000);
    }

    public void showDifference(){
        String brand = "local Brand";
        System.out.println(brand);
        System.out.println(this.brand);
    }

    public void changeBrand(String brand){
        this.brand = brand;
    }

    public void introduceYourself(){
        System.out.println("I am a car of brand " + this.brand.toUpperCase() + " from year " + this.yearOfProduction);
    }

    @Override
    public String toString() {
        return "Brand: " + brand + ", year of production: " + yearOfProduction;
    }
}
