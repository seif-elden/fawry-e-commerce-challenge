package org.example;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();


    public List<CartItem> getItems() { return items; }
    public boolean isEmpty() { return items.isEmpty(); }
    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
    public void clear() { items.clear(); }


    public void addItem(Products product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(Products product) {
        if (product == null) {
            System.out.println("Error: Cannot remove a null product from the cart.");
            return;
        }

        boolean removed = items.removeIf(item -> item.getProduct().getName() == product.getName());

        if (removed) {
            System.out.println(product.getName() + " removed from cart.");
        } else {
            System.out.println(product.getName() + " not found in cart.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cart Contents:\n");
        if (items.isEmpty()) {
            sb.append("  (empty)\n");
        } else {
            for (CartItem item : items) {
                sb.append("  ")
                        .append(item.getQuantity()).append("x ")
                        .append(item.getProduct().getName())
                        .append(" @ $").append(item.getProduct().getPrice())
                        .append(" each = $").append(item.getTotalPrice())
                        .append("\n");
            }
            sb.append("Subtotal: $").append(getSubtotal()).append("\n");
        }
        return sb.toString();
    }
}

