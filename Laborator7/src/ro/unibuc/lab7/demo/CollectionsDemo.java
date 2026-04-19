package ro.unibuc.lab7.demo;

import ro.unibuc.lab7.enums.CourseType;
import ro.unibuc.lab7.enums.StudentStatus;
import ro.unibuc.lab7.model.Course;
import ro.unibuc.lab7.model.Student;
import ro.unibuc.lab7.service.UniversityService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsDemo {

    public static void run() {
        System.out.println("=== COLLECTIONS DEMO ===");

        Student s1 = new Student("S1", "Ana", 20, StudentStatus.ACTIVE);
        Student s2 = new Student("S2", "Mihai", 22, StudentStatus.ACTIVE);
        Student s3 = new Student("S3", "Ioana", 21, StudentStatus.SUSPENDED);

        Course c1 = new Course("POO2", "Programare Orientata pe Obiecte II", CourseType.MANDATORY);
        Course c2 = new Course("BD", "Baze de Date", CourseType.LABORATORY);
        Course c3 = new Course("ENG", "Engleza", CourseType.OPTIONAL);

        UniversityService service = new UniversityService();

        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);

        service.addCourse(c1);
        service.addCourse(c2);
        service.addCourse(c3);
        service.addCourse(c1); // nu se va duplica in Set

        service.enrollStudent(s1, c1, 9.50);
        service.enrollStudent(s1, c2, 8.75);
        service.enrollStudent(s2, c1, 10.00);
        service.enrollStudent(s3, c3, 7.25);

        service.printAllData();

        System.out.println("\nMedia Anei = " + service.calculateAverageGrade("S1"));
        System.out.println("\nMedia unui student inexistent = " + service.calculateAverageGrade("unexpected"));

        demoList();
        demoSet();
        demoMap();

        System.out.println();
    }

    private static void demoList() {
        System.out.println("\n--- LIST DEMO ---");
        List<String> names = new ArrayList<>();

        names.add("Ana");
        names.add("Mihai");
        names.add("Ana"); // duplicat permis
        names.add(1, "Pavel");

        System.out.println("Lista: " + names);
        System.out.println("Element de pe pozitia 0: " + names.getFirst());

        names.set(0, "Elena");
        System.out.println("Dupa set: " + names);

        names.remove("Ana"); // prima aparitie
        System.out.println("Dupa remove: " + names);
    }

    private static void demoSet() {
        System.out.println("\n--- SET DEMO ---");
        Set<String> groups = new HashSet<>();

        groups.add("G1");
        groups.add("G2");
        groups.add("G1"); // duplicatul este ignorat

        System.out.println("Set: " + groups);
        System.out.println("Contine G2? " + groups.contains("G2"));

        Set<String> anotherSet = new HashSet<>();
        anotherSet.add("G2");
        anotherSet.add("G3");

        Set<String> union = new HashSet<>(groups);
        union.addAll(anotherSet);

        Set<String> intersection = new HashSet<>(groups);
        intersection.retainAll(anotherSet);

        Set<String> difference = new HashSet<>(groups);
        difference.removeAll(anotherSet);

        System.out.println("Reuniune: " + union);
        System.out.println("Intersectie: " + intersection);
        System.out.println("Diferenta: " + difference);
    }

    private static void demoMap() {
        System.out.println("\n--- MAP DEMO ---");
        Map<String, Integer> absencesByStudent = new LinkedHashMap<>();

        absencesByStudent.put("Ana", 2);
        absencesByStudent.put("Mihai", 1);
        absencesByStudent.put("Ioana", 4);
        absencesByStudent.put("Ana", 3); // suprascrie valoarea veche

        System.out.println("Map: " + absencesByStudent);
        System.out.println("Absente Ana: " + absencesByStudent.get("Ana"));
        System.out.println("Chei: " + absencesByStudent.keySet());
        System.out.println("Valori: " + absencesByStudent.values());

        for (Map.Entry<String, Integer> entry : absencesByStudent.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}