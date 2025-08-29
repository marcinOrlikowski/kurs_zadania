package oop;

public class Person {
    private String name;
    private int age;
    public static int numberOfPeople = 0;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        numberOfPeople++;
    }

    public Person(String name) {
        this(name, 0);
    }

    public void introduceYourself(){
        System.out.println(" Name: " + this.name + ", age: " + this.age);
    }

    public void introduceYourself(String greeting){
        System.out.print(greeting);
        this.introduceYourself();
    }

    public boolean isAdult(){
        return this.age >= 18;
    }

    public int compareAge(Person other){
        return this.age - other.age;
    }
}
