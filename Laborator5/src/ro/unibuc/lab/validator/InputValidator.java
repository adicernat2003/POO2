package ro.unibuc.lab.validator;

public class InputValidator {

    private InputValidator() {
    }

    public static int parsePositiveInt(String value) {
        int number = Integer.parseInt(value);

        if (number <= 0) {
            throw new IllegalArgumentException("Numarul trebuie sa fie pozitiv.");
        }

        return number;
    }
}