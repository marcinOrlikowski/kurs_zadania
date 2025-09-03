package BigDecimalAndString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

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

        System.out.println("TASK - 7");
        shoppingCartSum();

        System.out.println("TASK - 8");
        //averagePrice();

        System.out.println("TASK - 9");
        priceInDifferentCurrency();

        System.out.println("TASK - 10");
        precisionDifference();

        System.out.println("TASK - 11");
        stringBasicOperations();
    }

    private static void stringBasicOperations() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String input = sc.nextLine();
        System.out.println("Lenght: " + input.length());
        System.out.println("Uppercase version: " + input.toUpperCase());
        System.out.println("Lowercase version: " + input.toUpperCase());
        System.out.println("Reversed version: " + new StringBuilder(input).reverse());
    }

    private static void precisionDifference() {
        System.out.println("double: " + (0.1 + 0.2));
        System.out.println("BigDecimal: " + (new BigDecimal("0.1").add(new BigDecimal("0.2"))));
    }

    private static void priceInDifferentCurrency() {
        BigDecimal pln = BigDecimal.valueOf(199.99);
        BigDecimal euroExchangeRate = BigDecimal.valueOf(4.25);
        BigDecimal euro = pln.divide(euroExchangeRate, 2, RoundingMode.HALF_UP);
        System.out.println(euro);
    }

    private static void averagePrice() {
        BigDecimal sum = BigDecimal.ZERO;
        Scanner sc = new Scanner(System.in);
        System.out.println("How many prices do you want to add?");
        int numberOfProducts = sc.nextInt();
        sc.nextLine();
        BigDecimal[] products = new BigDecimal[numberOfProducts];
        for (int i = 0; i < products.length; i++) {
            System.out.println("Enter price of " + (i + 1) + " product");
            products[i] = new BigDecimal(sc.nextLine());
            sum = sum.add(products[i]);
        }
        BigDecimal averagePrice = sum.divide(BigDecimal.valueOf(numberOfProducts), 2, RoundingMode.HALF_UP);
        System.out.println("Average price: " + averagePrice);
    }

    private static void shoppingCartSum() {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal[] array = {
                BigDecimal.valueOf(7.99),
                BigDecimal.valueOf(9.99),
                BigDecimal.valueOf(14.99)
        };
        for (BigDecimal bigDecimal : array) {
            sum = sum.add(bigDecimal);
        }
        System.out.println("Sum of prices: " + sum);
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
