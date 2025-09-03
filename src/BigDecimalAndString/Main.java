package BigDecimalAndString;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        System.out.println("TASK - 1");
        simpleUseOfBigDecimal();

        System.out.println("TASK - 2");
        bigDecimalFromString();

        System.out.println("TASK - 3");
        roundingPrices();

        System.out.println("TASK - 4");
        calculatingDiscount();

        System.out.println("TASK - 5");
        vatTax();

        System.out.println("TASK - 6");
        comparingPrices();
    }

    private static void comparingPrices() {
        BigDecimal price1 = BigDecimal.valueOf(9.99);
        BigDecimal price2 = BigDecimal.valueOf(10);
        int i = price1.compareTo(price2);
        if (i < 0) {
            System.out.println("Price 2 is Bigger");
        } else if (i == 0) {
            System.out.println("Prices are equal");
        } else {
            System.out.println("Price 1 is Bigger");
        }
    }

    private static void vatTax() {
        BigDecimal netPrice = BigDecimal.valueOf(9.99);
        BigDecimal vat = BigDecimal.valueOf(0.23);
        BigDecimal grossPrice = netPrice.multiply(vat).add(netPrice).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Gross price: " + grossPrice);
    }

    private static void calculatingDiscount() {
        BigDecimal price = BigDecimal.valueOf(3.49);
        BigDecimal discountPercentage = BigDecimal.valueOf(0.15);
        BigDecimal afterDiscount = price.subtract(price.multiply(discountPercentage)).setScale(2, RoundingMode.HALF_UP);
        System.out.println(afterDiscount);
    }

    private static void roundingPrices() {
        BigDecimal bigDecimal = BigDecimal.valueOf(10.56789);
        BigDecimal rounded = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Before: " + bigDecimal);
        System.out.println("After: " + rounded);
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
