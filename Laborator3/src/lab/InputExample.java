package lab;

import lab.model.Device;
import lab.model.Phone;

import java.util.Scanner;

public class InputExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introdu brandul telefonului: ");
        String brand = scanner.nextLine();

        System.out.print("Introdu modelul telefonului: ");
        String model = scanner.nextLine();

        System.out.print("Introdu pretul: ");
        double price = scanner.nextDouble();

        System.out.print("Introdu nivelul bateriei: ");
        int battery = scanner.nextInt();

        Device phone = new Phone(brand, model, price, battery);

        System.out.println("\nTelefon creat:");
        phone.displayInfo();

        scanner.close();
    }
}
