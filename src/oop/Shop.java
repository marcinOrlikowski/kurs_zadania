package oop;

public class Shop {
    private String name;
    private Product[] products;

    public Shop(String name, Product[] products) {
        this.name = name;
        this.products = products;
    }

    public void showProducts(){
        for (Product product : this.products) {
            product.showProduct();
        }
    }
}
