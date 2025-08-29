package oop;

public class Car {
    String brand;
    int yearOfProduction;
    static int counter = 0;

    public Car(String brand, int yearOfProduction) {
        this.brand = brand;
        this.yearOfProduction = yearOfProduction;
        counter++;
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

    public static void classDescription(){
        System.out.println("This class represents cars.");
    }

    @Override
    public String toString() {
        return "Brand: " + brand + ", year of production: " + yearOfProduction;
    }
}
