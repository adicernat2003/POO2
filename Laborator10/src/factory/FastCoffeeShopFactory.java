package factory;

import model.Product;

public class FastCoffeeShopFactory implements CoffeeShopFactory {
    private final ProductFactory productFactory = new DefaultProductFactory();

    @Override
    public Product createProduct(String type) {
        return productFactory.createProduct(type);
    }

    @Override
    public String createPackaging() {
        return "Pahar takeaway standard";
    }
}