package lab.model;

import lab.interfaces.Connectable;
import lab.interfaces.Switchable;

public class Laptop extends Device implements Switchable, Connectable {
    private int ramGb;

    public Laptop(String brand, String model, double price, int ramGb) {
        super(brand, model, price);
        setRamGb(ramGb);
    }

    public void setRamGb(int ramGb) {
        if (ramGb <= 0) {
            throw new IllegalArgumentException("RAM-ul trebuie sa fie mai mare decat 0.");
        }
        this.ramGb = ramGb;
    }

    @Override
    public void useDevice() {
        System.out.println("Laptopul este folosit pentru programare, browsing si multimedia.");
    }

    @Override
    public void turnOn() {
        System.out.println(getModel() + " booteaza sistemul de operare.");
    }

    @Override
    public void turnOff() {
        System.out.println(getModel() + " se inchide.");
    }

    @Override
    public void connectToWiFi(String networkName) {
        System.out.println(getModel() + " s-a conectat la reteaua: " + networkName);
    }

    @Override
    public void displayInfo() {
        System.out.println("[LAPTOP] Brand: " + getBrand() + ", Model: " + getModel() +
                ", Pret: " + getPrice() + " lei, RAM: " + ramGb + " GB");
    }
}