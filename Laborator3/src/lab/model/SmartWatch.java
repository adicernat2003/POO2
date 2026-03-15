package lab.model;

import lab.interfaces.Connectable;
import lab.interfaces.Switchable;

public class SmartWatch extends Device implements Switchable, Connectable {
    private final boolean heartRateMonitor;

    public SmartWatch(String brand, String model, double price, boolean heartRateMonitor) {
        super(brand, model, price);
        this.heartRateMonitor = heartRateMonitor;
    }

    @Override
    public void useDevice() {
        System.out.println("Smartwatch-ul monitorizeaza activitatea fizica si notificarile.");
    }

    @Override
    public void turnOn() {
        System.out.println(getModel() + " porneste si afiseaza ceasul.");
    }

    @Override
    public void turnOff() {
        System.out.println(getModel() + " se opreste pentru economisirea bateriei.");
    }

    @Override
    public void connectToWiFi(String networkName) {
        System.out.println(getModel() + " se conecteaza la reteaua WiFi: " + networkName);
    }

    @Override
    public void displayInfo() {
        System.out.println("[SMARTWATCH] Brand: " + getBrand() + ", Model: " + getModel() +
                ", Pret: " + getPrice() + " lei, HR Monitor: " + heartRateMonitor);
    }
}
