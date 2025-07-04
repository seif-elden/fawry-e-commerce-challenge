package org.example;
import java.util.ArrayList;
import java.util.List;

public class ECommerceSystem {
    private List<Products> productCatalog;
    private List<Customer> customerDatabase;
    private ShippingService shippingService;

    public ECommerceSystem() {
        this.productCatalog = new ArrayList<>();
        this.customerDatabase = new ArrayList<>();
        this.shippingService = new ShippingService();
    }

    //not to add 2 products with same mail
    public void addProduct(Products product) {
        if (getProduct(product.getName()) == null){
            productCatalog.add(product);
            System.out.println("Product added to catalog: " + product.getName());
        }else {
            System.out.println("Error : Product already registered ");

        }
    }

    //not to add 2 customers with same mail
    public void addCustomer(Customer customer) {
        if (getCustomer(customer.getEmail()) == null){
            customerDatabase.add(customer);
            System.out.println("Customer registered: " + customer.getName());
        }else {
            System.out.println("Error : Customer already registered ");
        }
    }

    public Products getProduct(String name) {
        for (Products product : productCatalog) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null; // Product not found
    }

    public Customer getCustomer(String name) {
        for (Customer customer : customerDatabase) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    public void checkout(Customer customer, Cart cart) {
        System.out.println("\n--- Starting Checkout for " + customer.getName() + " ---");

        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty. Cannot proceed with checkout.");
            return;
        }

        double subtotal = 0;
        List<Shippable> itemsToShip = new ArrayList<>();
        List<CartItem> currentCartItems = cart.getItems();

        // Validate products in cart (out of stock, expired) and calculate subtotal
        for (CartItem cartItem : currentCartItems) {
            Products productInCart = cartItem.getProduct();
            int requestedQuantity = cartItem.getQuantity();

            Products catalogProduct = getProduct(productInCart.getName());

            if (catalogProduct == null) {
                System.out.println("Error: Product '" + productInCart.getName() + "' not found in our catalog.");
                return;
            }

            // Error: one product is out of stock or expired.
            if (!catalogProduct.isAvailable()) {
                System.out.println("Error: " + catalogProduct.getName() + " is out of stock or expired. Cannot complete checkout.");
                return;
            }
            if (catalogProduct.getQuantity() < requestedQuantity) {
                System.out.println("Error: Not enough " + catalogProduct.getName() + " in stock. Available: " + catalogProduct.getQuantity() + ", Requested: " + requestedQuantity + ".");
                return;
            }

            subtotal += productInCart.getPrice() * requestedQuantity;

            if (catalogProduct.requiresShipping()) {
                itemsToShip.add(new Shippable() {
                    @Override
                    public String getName() {
                        return requestedQuantity + "x " + productInCart.getName();
                    }

                    @Override
                    public double getWeight() {
                        return productInCart.getWeight() * requestedQuantity;
                    }
                });
            }
        }

        double shippingFees = shippingService.calculateShippingFee(itemsToShip);
        double paidAmount = subtotal + shippingFees;

        // Error: Customer's balance is insufficient.
        if (customer.getBalance() < paidAmount) {
            System.out.println("Error: Customer '" + customer.getName() + "' has insufficient balance. Required: $" + String.format("%.2f", paidAmount) + ", Available: $" + String.format("%.2f", customer.getBalance()) + ".");
            return;
        }

        // Process payment
        customer.reduceBalance(paidAmount);

        // Update quantity
        for (CartItem cartItem : currentCartItems) {
            Products product = getProduct(cartItem.getProduct().getName()); // Get the actual product from the catalog
            if (product != null) {
                product.decrementQuantity(cartItem.getQuantity());
            }
        }


        // Print checkout details
        System.out.println("\n** Checkout receipt **");
        for (CartItem cartItem : currentCartItems) {
            System.out.println(cartItem.getQuantity() + "x " + cartItem.getProduct().getName() + " " + String.format("%.0f", cartItem.getProduct().getPrice() * cartItem.getQuantity()));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + String.format("%.0f", subtotal) + "");
        System.out.println("Shipping " + String.format("%.0f", shippingFees) + "");
        System.out.println("Amount " + String.format("%.0f", paidAmount) + "");
        System.out.println("Customer current balance after payment: $" + String.format("%.2f", customer.getBalance()) + "");
        System.out.println("END.");

        cart.clear(); // Clear the cart after successful checkout
        System.out.println("--- Checkout Complete ---");
    }
}
