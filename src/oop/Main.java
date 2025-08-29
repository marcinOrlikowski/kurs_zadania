package oop;

import java.math.BigDecimal;

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

        //task 7
        car3.introduceYourself();
        car4.introduceYourself();
        car5.introduceYourself();

        //task 8
        Car.classDescription();

        //task 9
        System.out.println(Car.counter);

        //task 10
        Car[] cars = {
                new Car("Audi", 2020),
                new Car("BMW", 1998),
                new Car("Renault", 2001)
        };
        for (Car car : cars) {
            System.out.println(car);
        }

        //task 11
        Person marcin = new Person("Marcin", 29);
        Person mateusz = new Person("Mateusz", 26);
        marcin.introduceYourself();
        mateusz.introduceYourself();

        //task 12
        marcin.introduceYourself();
        marcin.introduceYourself("Hello");

        //task 13
        Person oneParameter = new Person("oneParameter");
        Person twoParameters = new Person("TwoParameters", 40);
        oneParameter.introduceYourself();
        twoParameters.introduceYourself();

        //task 14
        Person oneParameterWithThis = new Person("oneParameterWithThis");
        oneParameterWithThis.introduceYourself();

        //task 15
        System.out.println("Acces via class: " + Person.numberOfPeople);
        System.out.println("Acces via object: " + marcin.numberOfPeople);

        //task 16
        Person[] people = {
                new Person("Jacek", 21),
                new Person("Maciek", 16),
                new Person("Martyna", 25)
        };
        for (Person person : people) {
            if (person.isAdult()) {
                person.introduceYourself();
            }
        }

        //task 17
        System.out.println(marcin.compareAge(mateusz));

        //task 18
        new Product("Milk", new BigDecimal("2.99")).showProduct();
        new Product("Chocolate", new BigDecimal("7.99")).showProduct();
        new Product("Cereals", new BigDecimal("15.99")).showProduct();

        //task 19
        Product eggs = new Product("Eggs", new BigDecimal("10.99"));
        eggs.reducePrice(new BigDecimal(1));
        eggs.reducePrice(new BigDecimal(2),"High stock");

    }
}
