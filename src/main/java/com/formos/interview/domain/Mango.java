package com.formos.interview.domain;

public class Mango extends Product {

    public Mango(Integer inStock) {
        super("Mango", inStock);
    }

    @Override
    public Unit getUnit() {
        return Unit.g;
    }

    @Override
    public void decreaseProduct(int require) {
        if (canGet(require)) {
            inStock -= ((Double)(require * 1.4)).intValue();
        }
    }

    @Override
    public boolean canGet(int require) {
        if (require < 0 || inStock < 0 || inStock < (require * 1.4)) {
            return false;
        }
        return true;
    }

    @Override
    public Flavor getFlavor() {
        return Flavor.Mango;
    }
}
