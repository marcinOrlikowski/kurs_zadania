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
}
