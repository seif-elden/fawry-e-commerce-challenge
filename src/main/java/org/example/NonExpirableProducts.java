package org.example;

public class NonExpirableProducts extends Products  {
    private boolean requiresShip;
    private double weight;

    public NonExpirableProducts(double price, int quantity, String name , boolean requireShip , double weight) {
        super(price, quantity, name);

        // assume admin enter weight 0 for Nonshippable items
        this.requiresShip = requireShip ;
        this.weight = weight;
    }

    @Override
    public boolean requiresShipping() {
        return requiresShip;
    }

    @Override
    public boolean canExpire() {
        return false;
    }
    @Override
    public boolean isAvailable() {
        return getQuantity() > 0;
    }
    // this function is to get the weight of the product should be used on products that can be shipped only
    @Override
    public double getWeight() {
        return weight;
    }



}
