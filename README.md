# Java E-Commerce System: A Flexible Shopping Cart Implementation with Product Management
This Java-based e-commerce system provides a robust implementation of shopping cart functionality with support for expirable and non-expirable products, shipping calculations, and customer management. The system handles various checkout scenarios including insufficient balance, expired products, and out-of-stock situations.

The system implements core e-commerce features including product catalog management, customer accounts, shopping cart operations, and a comprehensive checkout process. It supports both physical products requiring shipping and digital products, along with expirable products like food items. The implementation follows object-oriented principles with clear separation of concerns and extensible design patterns.

## Repository Structure
```
.
├── pom.xml                 # Maven project configuration
└── src/main/java/org/example/
    ├── Main.java           # Application entry point with test scenarios
    ├── ECommerceSystem.java # Core system implementation
    ├── Cart.java           # Shopping cart implementation
    ├── CartItem.java       # Individual cart item representation
    ├── Customer.java       # Customer data model
    ├── Products.java       # Abstract product base class
    ├── ExpirableProducts.java    # Implementation for products with expiry dates
    ├── NonExpirableProducts.java # Implementation for non-expirable products
    ├── Shippable.java      # Interface for shippable items
    └── ShippingService.java # Shipping cost calculation service
```

## Usage Instructions
### Prerequisites
- Java Development Kit (JDK) 21 or higher
- Apache Maven 3.6.0 or higher
- Git (optional, for version control)

### Installation
1. Clone the repository:
```bash
git clone [repository-url]
cd [repository-name]
```

2. Build the project:
```bash
mvn clean install
```

### Quick Start
1. Create an instance of the e-commerce system:
```java
ECommerceSystem system = new ECommerceSystem();
```

2. Add products to the catalog:
```java
Products tv = new NonExpirableProducts(5000.0, 2, "TV", true, 15.0);
system.addProduct(tv);
```

3. Register customers:
```java
Customer customer = new Customer("Alice", "alice@example.com", "01012345678", 10000.0);
system.addCustomer(customer);
```

4. Create and process a cart:
```java
Cart cart = new Cart();
cart.addItem(system.getProduct("TV"), 1);
system.checkout(customer, cart);
```

### More Detailed Examples
1. Successful Checkout with Mixed Products:
```java
System.out.println("\nTEST CASE 1: Successful Checkout");
Cart cart1 = new Cart();
cart1.addItem(system.getProduct("Cheese"), 2); //
cart1.addItem(system.getProduct("TV"), 1);  // Changed from 3 to 1 to manage customer balance and make example clearer
cart1.addItem(system.getProduct("Mobile Scratch Card"), 1); //
cart1.addItem(system.getProduct("Biscuits"), 1); //
system.checkout(customer1, cart1);
System.out.println("\n======================================");
```
![image](https://github.com/user-attachments/assets/0cb49d05-1572-428b-9c9a-df47f71052f9)


2. Use Case 2: Cart is Empty Error:
```java
System.out.println("\nTEST CASE 2: Empty Cart Error");
Cart cart2 = new Cart();
system.checkout(customer1, cart2); //
System.out.println("\n======================================");
```
![image](https://github.com/user-attachments/assets/e1776172-fc54-4a0d-9d92-47d5b62e6aee)

3. Use Case 3: Customer's Balance is Insufficient Error
```java
System.out.println("\nTEST CASE 3: Insufficient Balance Error");
Cart cart3 = new Cart();
cart3.addItem(system.getProduct("TV"), 1); // Total price: 10000.0 + shipping
system.checkout(customer2, cart3); // Bob only has 150.0
System.out.println("\n======================================");
```
![image](https://github.com/user-attachments/assets/deae51a6-854e-475f-b7e5-e019ac47fe88)

4. Use Case 4: Product is Out of Stock Error
```java
System.out.println("\nTEST CASE 4: Out of Stock Error");
Cart cart4 = new Cart();
cart4.addItem(system.getProduct("Out of Stock Item"), 1);
system.checkout(customer1, cart4); //
System.out.println("\n======================================");
```
![image](https://github.com/user-attachments/assets/1cc1beda-6515-40bf-b6fb-6ae629582677)

5. Use Case 5: Product is Expired Error
```java
System.out.println("\nTEST CASE 5: Expired Product Error");
Cart cart5 = new Cart();
cart5.addItem(system.getProduct("Expired Cheese"), 1);
system.checkout(customer1, cart5); //
System.out.println("\n======================================");
```
![image](https://github.com/user-attachments/assets/f9dc6ba0-0b6b-41e7-8c8e-8fd6fe089465)

6. Use Case 6: Adding more than available quantity to cart (handled by Cart.addProduct)
```java
System.out.println("\nTEST CASE 6: Adding more than available quantity to cart");
Cart cart6 = new Cart();
cart6.addItem(system.getProduct("TV"), 5); // Only 1 TV left after first purchase
system.checkout(customer1, cart6);
System.out.println("\n======================================");
```
![image](https://github.com/user-attachments/assets/794fba23-1e81-4554-ba11-682710b48901)


### Troubleshooting
Common issues and solutions:

1. Insufficient Balance Error
```
Error: If you see "Customer has insufficient balance"
Solution: 
- Check customer's current balance using customer.getBalance()
- Add funds using customer.addBalance(amount)
```

2. Product Availability Issues
```
Error: "Product is out of stock or expired"
Solution:
- For expired products: Check expiration date
- For out of stock: Verify product quantity in catalog
```

3. Cart Validation Errors
```
Error: "Cart is empty"
Solution: Ensure items are added to cart before checkout
Debug: Print cart contents using cart.toString()
```

## Data Flow
The e-commerce system processes transactions through a defined workflow from cart creation to checkout completion.

```ascii
[Customer] -> [Cart]
     |          |
     v          v
[Product Catalog] -> [Shipping Service]
     |                    |
     v                    v
[Checkout Process] -> [Payment Processing]
```

Key component interactions:
1. Customer initiates shopping session
2. Cart collects and validates product selections
3. Product catalog verifies availability and pricing
4. Shipping service calculates delivery costs
5. Checkout process validates transaction
6. Payment processing updates customer balance
7. Inventory updates reflect purchased quantities
8. Transaction completes with receipt generation
---
This file was automatically created using a Python script integrated with Amazon Q but no AI was used in the making of the src of this project. 
