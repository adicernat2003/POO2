package ro.unibuc.lab;

import ro.unibuc.lab.exception.InvalidStudentDataException;
import ro.unibuc.lab.inheritance.AdvancedReporter;
import ro.unibuc.lab.inheritance.BaseReporter;
import ro.unibuc.lab.io.FileConfigLoader;
import ro.unibuc.lab.repository.StudentRepository;
import ro.unibuc.lab.service.CalculatorService;
import ro.unibuc.lab.service.DataProcessingService;
import ro.unibuc.lab.validator.InputValidator;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== LABORATOR EXCEPTII JAVA ===");

        demoRuntimeException();
        demoCheckedExceptionHandledLocally();
        demoPropagationWithThrows();
        demoCustomException();
        demoMultiCatch();
        demoFinally();
        demoOverrideExceptions();
    }

    private static void demoRuntimeException() {
        System.out.println("\n1. Runtime exception / unchecked");

        try {
            int result = InputValidator.parsePositiveInt("-5");
            System.out.println("Rezultat: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Exceptie runtime prinsa: " + e.getMessage());
        }
    }

    private static void demoCheckedExceptionHandledLocally() {
        System.out.println("\n2. Checked exception tratata local cu try-catch");

        FileConfigLoader loader = new FileConfigLoader();
        loader.loadConfigWithFinally("config.txt");
    }

    private static void demoPropagationWithThrows() {
        System.out.println("\n3. Propagarea exceptiei cu throws");

        DataProcessingService service = new DataProcessingService();

        try {
            service.processFile("data.txt");
        } catch (Exception e) {
            System.out.println("Exceptie prinsa in main: " + e.getClass().getSimpleName()
                    + " - " + e.getMessage());
        }
    }

    private static void demoCustomException() {
        System.out.println("\n4. Exceptie custom");

        StudentRepository repository = new StudentRepository();

        try {
            repository.saveStudent("", 17);
        } catch (InvalidStudentDataException e) {
            System.out.println("Exceptie custom prinsa: " + e.getMessage());
        }
    }

    private static void demoMultiCatch() {
        System.out.println("\n5. Multi-catch");

        CalculatorService calculatorService = new CalculatorService();

        try {
            calculatorService.computeAverage("abc", "0");
        } catch (ArithmeticException | NumberFormatException e) { // de la cel mai specific in sus
            System.out.println("Exceptie prinsa prin multi-catch: " + e.getClass().getSimpleName()
                    + " - " + e.getMessage());
        }
    }

    private static void demoFinally() {
        System.out.println("\n6. Bloc finally");

        FileConfigLoader loader = new FileConfigLoader();

        try {
            loader.loadConfigAndRethrow("missing-file.txt");
        } catch (Exception e) {
            System.out.println("Exceptie re-aruncata: " + e.getMessage());
        }
    }

    private static void demoOverrideExceptions() {
        System.out.println("\n7. Exceptii si overriding");

        AdvancedReporter reporter = new AdvancedReporter();

        try {
            reporter.generateReport();
        } catch (FileNotFoundException e) {
            System.out.println("Exceptie din metoda suprascrisa: " + e.getMessage());
        }

        BaseReporter baseReporter = new AdvancedReporter();

        try {
            baseReporter.generateReport();
        } catch (FileNotFoundException e) {
            System.out.println("Exceptie din metoda suprascrisa: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}