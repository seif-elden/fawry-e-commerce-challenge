package org.example;

public class CartItem {

    private Products product;
    private int quantity;
    private double totalPrice;

    public CartItem(Products product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;

    }

    public Products getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public double getTotalWeight() {
        return product.requiresShipping() ? product.getWeight() * quantity : 0;
    }



}