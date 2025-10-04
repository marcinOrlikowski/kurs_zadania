package ffwork.money;

public class Test {
    public static void main(String[] args) {
        //creating objects:
        Money money = Money.of("10");
        try {
            Money invalidAmount = Money.of("-5"); // throws IllegalArgumentException: Money value must be positive and in '123.45' format
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            Money invalidDouble = Money.of(6); // throws IllegalArgumentException: Enter value in string format
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // math operation and toString testing:
        Money added = money.add(Money.of("5"));
//        Money subtracted = money.subtract(Money.of("5")); // throws IllegalArgumentException: Subtract value is bigger than current one
//        Money multiplyByBigDecimal = money.multiply(null); // throws IllegalArgumentException: Cannot multiply by null
//        Money multiplyByDouble = money.multiply(-2); // IllegalArgumentException: Money cannot have negative value
        System.out.println("added " + added);
//        System.out.println("subtracted " + subtracted);
//        System.out.println("multiplyByBigDecimal " + multiplyByBigDecimal);
//        System.out.println("multiplyByDouble " + multiplyByDouble);

        //compareTo test:
//        System.out.println(added.compareTo(subtracted));

        Money first = Money.of("10.0");
        Money second = Money.of("10.00");
        System.out.println(first.equals(second));

    }
}
