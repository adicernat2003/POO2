package ro.unibuc.lab.util;

import java.util.Scanner;

public final class InputHelper {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputHelper() {
    }

    public static int readInt(String mesaj) {
        while (true) {
            System.out.print(mesaj);
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valoare invalida. Introdu un intreg.");
            }
        }
    }

    public static double readDouble(String mesaj) {
        while (true) {
            System.out.print(mesaj);
            try {
                return Double.parseDouble(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valoare invalida. Introdu un numar.");
            }
        }
    }

    public static String readLine(String mesaj) {
        System.out.print(mesaj);
        return SCANNER.nextLine();
    }
}