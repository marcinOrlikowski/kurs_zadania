package oop;

public class Main {
    public static void main(String[] args) {
        //task 1
        Car car1 = new Car();
        car1.brand = "Fiat";
        car1.yearOfProduction = 2010;
        System.out.println(car1);

        //task 2
        Car car2 = new Car();
        System.out.println(car2);

        //task 3
        Car car3 = new Car("Opel", 2015);
        Car car4 = new Car("VW", 2015);
        Car car5 = new Car("Audi", 2015);

        System.out.println(car3);
        System.out.println(car4);
        System.out.println(car5);

        //task 4
        car5.showDifference();

        //task 5
        System.out.println("Before: " + car5);
        car5.changeBrand("Seat");
        System.out.println("After: " + car5);

        //task 6
        Car car6 = new Car();
        System.out.println(car6);
    }
}
