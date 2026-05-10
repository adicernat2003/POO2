package factory;

import model.Product;

public interface ProductFactory {
    Product createProduct(String type);
}