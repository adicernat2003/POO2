package ro.unibuc.poo.lab8.demo;

import ro.unibuc.poo.lab8.model.Student;
import ro.unibuc.poo.lab8.service.NotificationService;
import ro.unibuc.poo.lab8.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceDemo {

    public static void run() {
        System.out.println("\n--- Method Reference Demo ---");

        List<Student> students = StudentService.generateStudents();

        students.forEach(System.out::println);

        students.stream()
                .map(Student::getName)
                .forEach(System.out::println);

        students.stream()
                .filter(StudentService::isExcellent)
                .forEach(StudentService::printStudent);

        Function<String, Integer> parser = Integer::parseInt;
        System.out.println("Numar parsat: " + parser.apply("123"));

        Supplier<ArrayList<Student>> listSupplier = ArrayList::new;
        ArrayList<Student> newList = listSupplier.get();

        newList.add(students.get(0));
        newList.add(students.get(1));

        System.out.println("Lista creata prin constructor reference:");
        newList.forEach(System.out::println);

        NotificationService service = new NotificationService();

        service.notifyUser(
                "Mesaj transmis prin method reference static.",
                NotificationService::printNotification
        );
    }
}