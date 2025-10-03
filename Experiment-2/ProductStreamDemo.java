import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map.Entry;

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String toString() {
        return name + " | " + category + " | Price: " + price;
    }
}

public class ProductStreamDemo {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200, "Electronics"),
            new Product("Phone", 800, "Electronics"),
            new Product("Shirt", 40, "Clothing"),
            new Product("Jeans", 60, "Clothing"),
            new Product("Blender", 100, "Home")
        );

        // Group by category
        Map<String, List<Product>> grouped = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));
        System.out.println("Grouped by category:");
        grouped.forEach((k, v) -> System.out.println(k + ": " + v));

        // Most expensive product in each category
        Map<String, Optional<Product>> maxByCategory = products.stream()
            .collect(Collectors.groupingBy(
                p -> p.category,
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
            ));
        System.out.println("\nMost expensive product in each category:");
        maxByCategory.forEach((k, v) -> System.out.println(k + ": " + v.orElse(null)));

        // Average price of all products
        double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage price of all products: " + avgPrice);
    }
}
