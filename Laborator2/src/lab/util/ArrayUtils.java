package lab.util;

public final class ArrayUtils {
    private ArrayUtils() {
        // prevenim instantierea
    }

    // metoda de clasa: nu depinde de starea unui obiect
    public static double average(double[] values) {
        if (values == null || values.length == 0) {
            return 0;
        }
        double sum = 0;

        // enhanced for
        for (double value : values) {
            sum += value;
        }

        int numberOfGrades = 0;
        for (double value : values) {
            if (value > 0.0) {
                numberOfGrades++;
            }
        }

        return sum / numberOfGrades;
    }

    public static void printIntArray(int[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + (i < values.length - 1 ? ", " : ""));
        }
        System.out.println();
    }
}
