package metody;

import java.util.Scanner;

public class Methods {
    public static void main(String[] args) {
        //task 6
        System.out.println("Enter age:");
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();
        sc.close();
        System.out.println(ageCategorization(age));
        /**
         * Outout:
         * Enter age:
         * 12
         * Teenager
         */
    }

    private static String ageCategorization(int age) {
        if (age < 0 || age > 150){
            return "Wrong age";
        } else if (age >= 65) {
            return "Senior";
        } else if (age >= 18) {
            return "Adult";
        } else if (age >= 12) {
            return "Teenager";
        } else {
            return "Child";
        }
    }

}

