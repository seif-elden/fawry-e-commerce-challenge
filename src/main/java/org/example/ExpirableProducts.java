package org.example;
import java.time.LocalDate;


public class ExpirableProducts extends Products  {
    private LocalDate expiryDate;
    private boolean requiresShip;
    private double weight;

    public ExpirableProducts(double price, int quantity, String name , LocalDate expiryDate , boolean requireShip , double weight) {
        super(price, quantity, name);
        this.expiryDate = expiryDate;

        // assume admin enter weight 0 for Nonshippable items
        this.requiresShip = requireShip ;
        this.weight = weight;
    }

    @Override
    public boolean isAvailable() {
        return getQuantity() > 0 && !isExpired();
    }

    @Override
    public boolean requiresShipping() {
        return requiresShip;
    }

    @Override
    public boolean canExpire() {
        return true;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    // this function is to get the weight of the product should be used on products that can be shipped only
    @Override
    public double getWeight() {
        return weight;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }



}
