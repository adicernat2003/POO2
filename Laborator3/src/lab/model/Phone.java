package lab.model;

import lab.interfaces.Connectable;
import lab.interfaces.Switchable;

public class Phone extends Device implements Switchable, Connectable {
    private int batteryLevel;

    public Phone(String brand, String model, double price, int batteryLevel) {
        super(brand, model, price);
        setBatteryLevel(batteryLevel);
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Battery level trebuie sa fie intre 0 si 100.");
        }
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void useDevice() {
        System.out.println("Telefonul este folosit pentru apeluri, mesaje si aplicatii.");
    }

    @Override
    public void turnOn() {
        System.out.println(getModel() + " se porneste.");
    }

    @Override
    public void turnOff() {
        System.out.println(getModel() + " se opreste.");
    }

    @Override
    public void connectToWiFi(String networkName) {
        System.out.println(getModel() + " s-a conectat la reteaua WiFi: " + networkName);
    }

    @Override
    public void displayInfo() {
        System.out.println("[PHONE] Brand: " + getBrand() + ", Model: " + getModel() +
                ", Pret: " + getPrice() + " lei, Baterie: " + batteryLevel + "%");
    }
}