package org.example;


// abstract product class
public abstract  class Products implements Shippable{
    private String name;
    private double price;
    private int quantity;


    public Products(double price, int quantity, String name) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    // setters and getters
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "products{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    // Decrement quantity after purchase
    public void decrementQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            throw new IllegalArgumentException("Not enough stock for " + name);
        }
    }

    // to handle shippable and expirable products
    public abstract boolean isAvailable();
    public abstract boolean requiresShipping();
    public abstract boolean canExpire();


}
