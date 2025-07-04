package org.example;
import java.util.List;

public class ShippingService {
    // price of shipping
    private static final double BASE_SHIPPING_FEE = 10.0;
    private static final double PER_KG_SHIPPING_FEE = 20.0;

    public double calculateShippingFee(List<Shippable> shippableItems) {
        double totalWeight = 0;
        System.out.println("** Shipment notice **");
        for (Shippable item : shippableItems) {
            double weightGrams = item.getWeight() * 1000;
            totalWeight += item.getWeight();
            System.out.printf("%s    %.0fg%n", item.getName(), weightGrams);
        }
        System.out.printf("Total package weight %.1fkg%n%n", totalWeight);

        return BASE_SHIPPING_FEE + (totalWeight * PER_KG_SHIPPING_FEE);
    }
}
