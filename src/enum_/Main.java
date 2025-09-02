package enum_;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1");
        DayOfWeek friday = DayOfWeek.FRIDAY;
        System.out.println(friday);

        System.out.println("Task 2");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day);
        }

        System.out.println("Task 3");
        DayOfWeek monday = DayOfWeek.MONDAY;
        DayOfWeek tuesday = DayOfWeek.TUESDAY;
        System.out.println(monday == tuesday);

        System.out.println("Task 4");
        enumInSwitch();

        System.out.println("Task 5");
        System.out.println(DayOfWeek.SATURDAY.isWeekend());

        System.out.println("Task 6");
        enumWithFieldAndConstructor();

        System.out.println("Task 7");
        System.out.println(Month.AUGUST);

        System.out.println("Task 8");
        enumOrdinal();

        System.out.println("Task 9");
        holidayMonths();

        System.out.println("Task 10");
        new Person("Marcin", DayOfWeek.SATURDAY).showDayOff();
        new Person("Maciek", DayOfWeek.SUNDAY).showDayOff();

        System.out.println("Task 11");
        enumInArray();

        System.out.println("Task 12");
        for (Currency currency : Currency.values()) {
            System.out.println(currency + " " + currency.getSymbol());
        }

        System.out.println("Task 13");
        Calculate();

        System.out.println("Task 14");
        OrderStatusChecker();

        System.out.println("Task 15");
        GetPlanetsDensity();

        System.out.println("Task 16");
        Logger.INSTANCE.log("test");

        System.out.println("Task 17");
        menu();

        System.out.println("Task 18");
        arrayOfProducts();

        System.out.println("Task 19");
        Calculations();
    }

    private static void Calculations() {
        System.out.println("a + b = " + Calculation.ADD.calculate(5, 5));
        System.out.println("a - b = " + Calculation.SUBTRACT.calculate(5, 5));
        System.out.println("a * b = " + Calculation.MULTIPLY.calculate(5, 5));
        System.out.println("a / b = " + Calculation.DIVIDE.calculate(5, 5));
    }

    private static void arrayOfProducts() {
        Product[] products = {
                new Product("Apple", Category.FOOD),
                new Product("t-shirt", Category.CLOTHING),
                new Product("laptop", Category.ELECTRONICS)
        };
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose option: ");
        System.out.println("'START', 'STOP', 'EXIT'");
        MenuOption userInput = MenuOption.valueOf(sc.nextLine().toUpperCase().trim());
        switch (userInput) {
            case START -> {
                System.out.println("STARTING...");
            }
            case STOP -> {
                System.out.println("STOPPING...");
            }
            case EXIT -> {
                System.out.println("GOODBYE");
            }
        }
    }

    private static void GetPlanetsDensity() {
        for (Planet planet : Planet.values()) {
            System.out.printf("%s : %f \n", planet, planet.getDensity());
        }
    }

    private static void OrderStatusChecker() {
        System.out.println(OrderStatus.NEW + " is active? : " + OrderStatus.NEW.isActive());
        System.out.println(OrderStatus.PROCESSING + " is active? : " + OrderStatus.PROCESSING.isActive());
        System.out.println(OrderStatus.SHIPPED + " is active? : " + OrderStatus.SHIPPED.isActive());
        System.out.println(OrderStatus.DELIVERED + " is active? : " + OrderStatus.DELIVERED.isActive());
        System.out.println(OrderStatus.CANCELED + " is active? : " + OrderStatus.CANCELED.isActive());
    }

    private static void Calculate() {
        Operation.ADD.calculate(5, 5);
        Operation.SUBTRACT.calculate(5, 5);
        Operation.MULTIPLY.calculate(5, 5);
        Operation.DIVIDE.calculate(5, 5);
    }

    private static void enumInArray() {
        Month[] months = {Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL};
        int sumOfDays = 0;
        for (Month month : months) {
            sumOfDays += month.getDays();
        }
        System.out.println("Sum of days is: " + sumOfDays);
    }

    private static void holidayMonths() {
        for (Month month : Month.values()) {
            if (month.isHolidaySeason()) {
                System.out.println(month);
            }
        }
    }

    private static void enumOrdinal() {
        for (Month month : Month.values()) {
            System.out.println(month + " " + month.ordinal());
        }
        int i = Month.JANUARY.compareTo(Month.FEBRUARY);
        System.out.println(i);
    }

    private static void enumWithFieldAndConstructor() {
        for (Month month : Month.values()) {
            System.out.println(month + " " + month.getDays());
        }
    }

    private static void enumInSwitch() {
        switch (DayOfWeek.TUESDAY) {
            case MONDAY, TUESDAY -> System.out.println("Beginning of the week");
            case WEDNESDAY, THURSDAY, FRIDAY -> System.out.println("Middle of the week");
            case SATURDAY, SUNDAY -> System.out.println("Weekend");
        }
    }
}
