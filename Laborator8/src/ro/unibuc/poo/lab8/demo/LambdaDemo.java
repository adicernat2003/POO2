package ro.unibuc.poo.lab8.demo;

import ro.unibuc.poo.lab8.functional.GradeEvaluator;
import ro.unibuc.poo.lab8.functional.NotificationSender;
import ro.unibuc.poo.lab8.functional.StudentTransformer;
import ro.unibuc.poo.lab8.model.Student;
import ro.unibuc.poo.lab8.service.StudentService;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaDemo {

    public static void run() {
        System.out.println("\n--- Lambda Demo ---");

        List<Student> students = StudentService.generateStudents();

        NotificationSender smsSender = message ->
                System.out.println("SMS: " + message);

        smsSender.send("Laboratorul de Java incepe acum.");

        GradeEvaluator passedEvaluator = student -> student.getGrade() >= 5;

        StudentTransformer transformer = student ->
                student.getName() + " are nota " + student.getGrade();

        Student firstStudent = students.getFirst();

        System.out.println("A promovat? " + passedEvaluator.evaluate(firstStudent));
        System.out.println(transformer.transform(firstStudent));

        Predicate<Student> hasScholarship = student -> student.hasScholarship();
        Function<Student, String> nameExtractor = student -> student.getName();
        Consumer<Student> printer = student -> System.out.println(student);
        Supplier<String> messageSupplier = () -> "Exemplu cu Supplier";

        System.out.println(messageSupplier.get());

        students.stream()
                .filter(hasScholarship)
                .map(nameExtractor)
                .forEach(System.out::println);

        printer.accept(firstStudent);
    }
}