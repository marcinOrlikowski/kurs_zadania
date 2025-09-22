package ffwork.money;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        //creating objects:
        Money money = Money.of("5");
        Money added = money.add(Money.of("5"));
        try {
            Money invalidAmount = Money.of("-5"); // throws IllegalArgumentException: Money cannot have negative value
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            Money invalidDouble = Money.of(6); // throws IllegalArgumentException: Enter value in string format
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // math operation and toString testing:
        Money subtracted = money.subtract(Money.of("5"));
        Money multiplyByBigDecimal = money.multiply(new BigDecimal("2"));
        Money multiplyByDouble = money.multiply(2);
        System.out.println("added " + added);
        System.out.println("subtracted " + subtracted);
        System.out.println("multiplyByBigDecimal " + multiplyByBigDecimal);
        System.out.println("multiplyByDouble " + multiplyByDouble);

        //compareTo test:
        System.out.println(added.compareTo(subtracted));

    }
}
