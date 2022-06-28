package com.formos.interview.domain;

public class Sugar extends Product {
    public Sugar(Integer inStock) {
        super("Sugar", inStock);
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
        return inStock > 0 && require > 0 && inStock >= require;
    }

    @Override
    public Flavor getFlavor() {
        return Flavor.None;
    }
}
