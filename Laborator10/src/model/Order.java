package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    /*
     Builder Pattern: construim obiectul pas cu pas.

     Avantaje:
     - cod mai lizibil
     - evita constructori foarte lungi
     - ușor de extins
    */

    private final String customerName;
    private final List<Product> products;
    private final int priority;

    private Order(Builder builder) {
        this.customerName = builder.customerName;
        this.products = Collections.unmodifiableList(builder.products);
        this.priority = builder.priority;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getPriority() {
        return priority;
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", products=" + products.stream().map(Product::getName).toList() +
                ", priority=" + priority +
                ", total=" + getTotalPrice() +
                '}';
    }

    public static class Builder {
        private final String customerName;
        private final List<Product> products = new ArrayList<>();
        private int priority = 1;

        public Builder(String customerName) {
            this.customerName = customerName;
        }

        public Builder addProduct(Product product) {
            this.products.add(product);
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Order build() {
            if (products.isEmpty()) {
                throw new IllegalStateException("Comanda trebuie sa contina cel putin un produs.");
            }

            return new Order(this);
        }
    }
}