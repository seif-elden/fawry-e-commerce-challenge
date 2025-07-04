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
1. Working with expirable products:
```java
// Create an expirable product with 10 days until expiration
Products cheese = new ExpirableProducts(
    100.0,                          // price
    5,                              // quantity
    "Cheese",                       // name
    LocalDate.now().plusDays(10),   // expiry date
    true,                           // requires shipping
    0.2                            // weight in kg
);
system.addProduct(cheese);
```

2. Processing a mixed cart:
```java
Cart cart = new Cart();
cart.addItem(system.getProduct("Cheese"), 2);
cart.addItem(system.getProduct("TV"), 1);
cart.addItem(system.getProduct("Mobile Scratch Card"), 1);
system.checkout(customer, cart);
```

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
