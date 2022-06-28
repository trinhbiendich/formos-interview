package com.formos.interview.domain;

public class Strawberry extends Product {

    public Strawberry(Integer inStock) {
        super("Strawberry", inStock);
    }

    @Override
    public Unit getUnit() {
        return Unit.g;
    }

    @Override
    public void decreaseProduct(int require) {
        if (canGet(require)) {
            inStock -= require;
        }
    }

    @Override
    public boolean canGet(int require) {
        if (require < 0 || inStock < 0 || inStock < require) {
            return false;
        }
        return true;
    }

    @Override
    public Flavor getFlavor() {
        return Flavor.Strawberry;
    }

}
