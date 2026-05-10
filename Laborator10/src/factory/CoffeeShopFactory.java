package factory;

import model.Product;

public interface CoffeeShopFactory {
    Product createProduct(String type);

    String createPackaging();
}