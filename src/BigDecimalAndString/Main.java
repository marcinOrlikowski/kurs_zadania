package BigDecimalAndString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
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
        averagePrice();

        System.out.println("TASK - 9");
        priceInDifferentCurrency();

        System.out.println("TASK - 10");
        precisionDifference();

        System.out.println("TASK - 11");
        stringBasicOperations();

        System.out.println("TASK - 12");
        stringSearching();

        System.out.println("TASK - 13");
        stringReplaceFragment();

        System.out.println("TASK - 14");
        stringComparison();

        System.out.println("TASK - 15");
        stringSplitting();

        System.out.println("TASK - 16");
        stringPrefixAndSuffix();

        System.out.println("TASK - 17");
        combineStringAndBigDecimal();

        System.out.println("TASK - 18");
        formattingPrices();

        System.out.println("TASK - 19");
        spittingABill();

        System.out.println("TASK - 20");
        roundingUp();
    }

    private static void roundingUp() {
        BigDecimal price = BigDecimal.valueOf(0.795);
        BigDecimal roundedUp = price.setScale(2, RoundingMode.CEILING);
        System.out.println(roundedUp);
    }

    private static void spittingABill() {
        BigDecimal bill = BigDecimal.valueOf(100);
        int numberOfPeople = 3;
        BigDecimal eachPersonPay = bill.divide(BigDecimal.valueOf(numberOfPeople), 2, RoundingMode.HALF_UP);
        System.out.println("Total bill: " + bill + " zł");
        System.out.println("Number of people: " + numberOfPeople);
        System.out.println("Each pays: " + eachPersonPay + " zł");
    }

    private static void formattingPrices() {
        List<BigDecimal> bigDecimals = List.of(
                new BigDecimal("0"),
                new BigDecimal("9.99"),
                new BigDecimal("19.99"),
                new BigDecimal("99.99")
        );
        bigDecimals.forEach(bd -> System.out.println(bd.setScale(2) + " zł"));
    }

    private static void combineStringAndBigDecimal() {
        String name = "laptop";
        BigDecimal price = BigDecimal.valueOf(3999.99);
        System.out.printf("Product: %s, price: %.2f PLN \n", name, price);
    }

    private static void stringPrefixAndSuffix() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String input = sc.nextLine();
        System.out.println("Does it starts with \"Java\"? " + input.toLowerCase().startsWith("java"));
        System.out.println("Does it ends with \"2025\"? " + input.endsWith("2025"));
    }

    private static void stringSplitting() {
        String sentence = "Ala ma kota";
        String[] stringArray = sentence.split(" ");
        for (String s : stringArray) {
            System.out.println(s);
        }
    }

    private static void stringComparison() {
        String sentence1 = "Ala ma kota";
        String sentence2 = "ala ma kota";
        System.out.println("equals: " + sentence1.equals(sentence2));
        System.out.println("equalsIgnoreCase: " + sentence1.equalsIgnoreCase(sentence2));
    }

    private static void stringReplaceFragment() {
        String sentence = "Ala ma kota, kot ma Ale";
        String result = sentence.replaceAll(" ", "_");
        System.out.println(result);
        System.out.println(result.replaceAll(",", ""));
    }

    private static void stringSearching() {
        String sentence = "Ala ma kota";
        String word = "ma";
        System.out.println("Does sentence contains word? : " + sentence.contains(word));
        System.out.println("At what index? : " + sentence.indexOf(word));
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
