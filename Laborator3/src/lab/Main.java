package lab;

import lab.interfaces.Connectable;
import lab.model.Device;
import lab.model.Laptop;
import lab.model.Person;
import lab.model.Phone;
import lab.model.SmartWatch;
import lab.util.Calculator;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. ENCAPSULARE ===");
        Person student = new Person("Andrei", 21);
        student.introduce();

        // acces controlat prin getter / setter
        student.setAge(22);
        System.out.println("Varsta actualizata: " + student.getAge());

        System.out.println("\n=== 2. SUPRAINCARCARE ===");
        System.out.println("add(int, int): " + Calculator.add(2, 3));
        System.out.println("add(int, int, int): " + Calculator.add(2, 3, 4));
        System.out.println("add(double, double): " + Calculator.add(2.5, 3.7));
        System.out.println("multiply(int, double): " + Calculator.multiply(2, 3.5));
        System.out.println("multiply(double, int): " + Calculator.multiply(2.5, 4));

        System.out.println("\n=== 3. MOSTENIRE + CLASE ABSTRACTE ===");
        Phone phone = new Phone("Samsung", "Galaxy S24", -1, 88);
        Laptop laptop = new Laptop("Lenovo", "ThinkPad E14", 3500, 16);
        SmartWatch watch = new SmartWatch("Huawei", "Watch GT", 900, true);

        phone.displayInfo();
        laptop.displayInfo();
        watch.displayInfo();

        System.out.println("\n=== 4. SUPRASCRIERE ===");
        phone.useDevice();
        laptop.useDevice();
        watch.useDevice();

        System.out.println("\n=== 5. INTERFETE ===");
        phone.turnOn();
        phone.connectToWiFi("Campus-WiFi");
        phone.turnOff();

        Connectable.printNetworkRules();
        phone.showConnectionType();

        System.out.println("\n=== 6. POLIMORFISM ===");
        Device[] devices = {
                phone,
                laptop,
                watch
        };

        for (Device device : devices) {
            device.displayInfo();   // apelul real depinde de obiectul concret
            device.useDevice();     // polimorfism la runtime
            System.out.println("---");
        }
    }
}