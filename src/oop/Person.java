package oop;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
        this.age = 0;
    }

    public void introduceYourself(){
        System.out.println(" Name: " + this.name + ", age: " + this.age);
    }

    public void introduceYourself(String greeting){
        System.out.print(greeting);
        this.introduceYourself();
    }
}
