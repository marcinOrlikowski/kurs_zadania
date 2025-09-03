package BigDecimalAndString;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        System.out.println("TASK - 1");
        simpleUseOfBigDecimal();

        System.out.println("TASK - 2");
        bigDecimalFromString();
    }

    private static void bigDecimalFromString() {
        BigDecimal bigDecimalFromString = new BigDecimal("9.99");
        BigDecimal bigDecimalFromDouble = new BigDecimal(9.99);
        System.out.println("From String: " + bigDecimalFromString);
        System.out.println("From double: " + bigDecimalFromDouble);
    }

    private static void simpleUseOfBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal("10.50");
        System.out.println("first bigDecimal: " + bigDecimal);
        BigDecimal bigDecimal2 = new BigDecimal("2.25");
        System.out.println("sum: " + bigDecimal.add(bigDecimal2));
        System.out.println("difference: " + bigDecimal.subtract(bigDecimal2));
        System.out.println("product: " + bigDecimal.multiply(bigDecimal2));
        System.out.println("quotient " + bigDecimal.divide(bigDecimal2, RoundingMode.HALF_UP));
    }
}
