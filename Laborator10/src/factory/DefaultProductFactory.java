package factory;

import model.Coffee;
import model.Product;
import model.Tea;

public class DefaultProductFactory implements ProductFactory {
    @Override
    public Product createProduct(String type) {
        return switch (type.toLowerCase()) {
            case "espresso" -> new Coffee("Espresso", 10.0);
            case "latte" -> new Coffee("Latte", 15.0);
            case "tea" -> new Tea("Green Tea", 8.0);
            default -> throw new IllegalArgumentException("Produs necunoscut: " + type);
        };
    }
}