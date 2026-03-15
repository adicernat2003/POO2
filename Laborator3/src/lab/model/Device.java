package lab.model;

public abstract class Device {
    private final String brand;
    private final String model;
    private double price;

    protected Device(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        setPrice(price);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Pretul nu poate fi negativ.");
        }
        this.price = price;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Pret: " + price + " lei");
    }

    public abstract void useDevice();
}
