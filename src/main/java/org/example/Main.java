package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ECommerceSystem system = new ECommerceSystem();

        // 1. Define Products
        Products cheese = new ExpirableProducts( 100.0, 5,"Cheese", LocalDate.now().plusDays(10), true, 0.2); // 200g
        Products biscuits = new ExpirableProducts( 50.0, 3,"Biscuits", LocalDate.now().plusMonths(2), true, 0.35); // 350g
        Products tv = new NonExpirableProducts( 5000.0, 2,"TV", true, 15.0);
        Products mobile = new NonExpirableProducts( 3000.0, 4,"Mobile", true, 0.5);
        Products scratchCard = new NonExpirableProducts( 100.0, 10,"Mobile Scratch Card", false, 0.0);
        Products expiredCheese = new ExpirableProducts( 80.0, 1,"Expired Cheese", LocalDate.now().minusDays(5), true, 0.1);
        Products outOfStockItem = new NonExpirableProducts( 20.0, 0,"Out of Stock Item", false, 0.0);


        system.addProduct(cheese);
        system.addProduct(biscuits);
        system.addProduct(tv);
        system.addProduct(mobile);
        system.addProduct(scratchCard);
        system.addProduct(expiredCheese);
        system.addProduct(outOfStockItem);


        // 2. Define Customers
        Customer customer1 = new Customer("Alice", "alice@example.com", "01012345678", 10000.0);
        Customer customer2 = new Customer("Bob", "bob@example.com", "01298765432", 150.0);

        system.addCustomer(customer1);
        system.addCustomer(customer2);



        // --- Use Case 1: Successful Checkout with Mixed Products ---
        System.out.println("\nTEST CASE 1: Successful Checkout");
        Cart cart1 = new Cart();
        cart1.addItem(system.getProduct("Cheese"), 2); //
        cart1.addItem(system.getProduct("TV"), 1);  // Changed from 3 to 1 to manage customer balance and make example clearer
        cart1.addItem(system.getProduct("Mobile Scratch Card"), 1); //
        cart1.addItem(system.getProduct("Biscuits"), 1); //

        system.checkout(customer1, cart1);

        System.out.println("\n======================================");



        // --- Use Case 2: Cart is Empty Error ---
        System.out.println("\nTEST CASE 2: Empty Cart Error");
        Cart cart2 = new Cart();
        system.checkout(customer1, cart2); //

        System.out.println("\n======================================");


        // --- Use Case 3: Customer's Balance is Insufficient Error ---
        System.out.println("\nTEST CASE 3: Insufficient Balance Error");
        Cart cart3 = new Cart();
        cart3.addItem(system.getProduct("TV"), 1); // Total price: 10000.0 + shipping
        system.checkout(customer2, cart3); // Bob only has 150.0
        System.out.println("\n======================================");



        // --- Use Case 4: Product is Out of Stock Error ---
        System.out.println("\nTEST CASE 4: Out of Stock Error");
        Cart cart4 = new Cart();
        cart4.addItem(system.getProduct("Out of Stock Item"), 1);
        system.checkout(customer1, cart4); //
        System.out.println("\n======================================");



        // --- Use Case 5: Product is Expired Error ---
        System.out.println("\nTEST CASE 5: Expired Product Error");
        Cart cart5 = new Cart();
        cart5.addItem(system.getProduct("Expired Cheese"), 1);
        system.checkout(customer1, cart5); //
        System.out.println("\n======================================");




        // --- Use Case 6: Adding more than available quantity to cart (handled by Cart.addProduct) ---
        System.out.println("\nTEST CASE 6: Adding more than available quantity to cart");
        Cart cart6 = new Cart();
        cart6.addItem(system.getProduct("TV"), 5); // Only 1 TV left after first purchase
        system.checkout(customer1, cart6);
        System.out.println("\n======================================");


    }
}