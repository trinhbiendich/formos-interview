package com.formos.interview.domain;

public class CondensedMilk extends Product {
    public CondensedMilk(Integer inStock) {
        super("Condensed milk", inStock);
    }

    @Override
    public Unit getUnit() {
        return Unit.ml;
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
