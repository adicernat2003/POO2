package lab.util;

public final class Calculator {

    private Calculator() {
    }

    // supraincarcare prin numar diferit de parametri
    public static int add(int a, int b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    // supraincarcare prin tip diferit de parametri
    public static double add(double a, double b) {
        return a + b;
    }

    // supraincarcare prin ordine diferita a parametrilor
    public static double multiply(int a, double b) {
        return a * b;
    }

    public static double multiply(double a, int b) {
        return a * b;
    }
}
