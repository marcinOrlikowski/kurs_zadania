package oop;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public void showProduct() {
        System.out.println("Name: " + name + ", price: " + price);
    }

    public void reducePrice(BigDecimal amount) {
        this.price = this.price.subtract(amount);
        System.out.println("New price of " + name.toUpperCase() + " " + price);
    }

    public void reducePrice(BigDecimal amount, String reason){
        this.reducePrice(amount);
        System.out.println("Price reduced due to: " + reason);
    }
}
