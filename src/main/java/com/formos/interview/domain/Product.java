package com.formos.interview.domain;

public abstract class Product {
    public String productName;
    public Integer inStock;
    public enum Unit {
        g, ml
    }
    public enum Flavor {
        Banana, Mango, Strawberry, None
    }

    public abstract Unit getUnit();
    public abstract Flavor getFlavor();
    public abstract void decreaseProduct(int require);
    public abstract boolean canGet(int require);

    public Product(String productName, Integer inStock) {
        this.inStock = inStock;
        this.productName = productName;
    }

    public String viewDetail() {
        return String.format("Name: %s, Instock: %s%s", productName, inStock, getUnit());
    }
}
