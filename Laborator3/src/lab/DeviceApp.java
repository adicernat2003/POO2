package lab;

import lab.model.Device;
import lab.model.Laptop;
import lab.model.Phone;

import java.util.Scanner;

public class DeviceApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Cate dispozitive vrei sa introduci?");
        int n = scanner.nextInt();

        Device[] devices = new Device[n];

        for (int i = 0; i < n; i++) {

            System.out.println("Tip dispozitiv (1 - Phone, 2 - Laptop):");
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Brand:");
            String brand = scanner.nextLine();

            System.out.println("Model:");
            String model = scanner.nextLine();

            System.out.println("Pret:");
            double price = scanner.nextDouble();

            devices[i] = switch (type) {
                case 1:
                    System.out.println("Nivel baterie:");
                    int battery = scanner.nextInt();
                    yield new Phone(brand, model, price, battery);
                case 2:
                    System.out.println("RAM (GB):");
                    int ram = scanner.nextInt();
                    yield new Laptop(brand, model, price, ram);
                default:
                    System.out.println("Tip dispozitiv invalid. Se va crea un dispozitiv generic.");
                    yield new Device(brand, model, price) {
                        @Override
                        public void useDevice() {
                            System.out.println("Dispozitivul este folosit.");
                        }
                    };
            };
            scanner.nextLine();
        }

        System.out.println("\n=== Dispozitive introduse ===");

        for (Device d : devices) {
            d.displayInfo();
            d.useDevice();
        }

        scanner.close();
    }
}
