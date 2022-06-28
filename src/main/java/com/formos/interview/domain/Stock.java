package com.formos.interview.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stock {
    private List<Product> products;

    public Stock() {

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void initTestValue(int minimum, int maximum) {
        products = new ArrayList<>();
        products.add(new Banana(getRandomNumber(minimum, maximum)));
        products.add(new CondensedMilk(getRandomNumber(minimum, maximum)));
        products.add(new Ice(getRandomNumber(minimum, maximum)));
        products.add(new Mango(getRandomNumber(minimum, maximum)));
        products.add(new Strawberry(getRandomNumber(minimum, maximum)));
        products.add(new Sugar(getRandomNumber(minimum, maximum)));
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        if (product == null) {
            return;
        }
        products.add(product);
    }

    public void view() {
        if (products == null) {
            System.out.println("There are nothing in stocks");
            return;
        }
        System.out.println(products.stream()
                        .map(product -> product.viewDetail())
                .collect(Collectors.joining("\n")));
    }

    public List<Product> allProducts() {
        return products;
    }
}
