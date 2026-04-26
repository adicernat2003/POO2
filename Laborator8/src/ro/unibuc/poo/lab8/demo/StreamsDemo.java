package ro.unibuc.poo.lab8.demo;

import ro.unibuc.poo.lab8.model.Course;
import ro.unibuc.poo.lab8.model.Student;
import ro.unibuc.poo.lab8.service.StudentService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void run() {
        System.out.println("\n--- Streams API Demo with Course ---");

        Course course = StudentService.generateJavaCourse();
        List<Student> students = course.getStudents();

        System.out.println("\nCurs curent:");
        System.out.println(course);

        System.out.println("\nStudentii cursului:");
        course.getStudents().forEach(System.out::println);

        System.out.println("\nStudenti promovati:");
        course.getPassedStudents().forEach(System.out::println);

        System.out.println("\nStudenti bursieri:");
        course.getScholarshipStudents().forEach(System.out::println);

        System.out.println("\nNumele studentilor:");
        List<String> names = students.stream()
                .map(Student::getName)
                .toList();

        System.out.println(names);

        System.out.println("\nStudenti sortati descrescator dupa nota, apoi crescator dupa nume:");
        students.stream()
                .sorted(
                        Comparator.comparing(Student::getGrade).reversed()
                                .thenComparing(Student::getName)
                )
                .forEach(System.out::println);

        System.out.printf("Media notelor: %.2f%n", course.getAverageGrade());

        System.out.println("\nStudenti grupati dupa grupa:");
        Map<String, List<Student>> studentsByGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGroup));

        studentsByGroup.forEach((group, groupStudents) -> {
            System.out.println("Grupa " + group + ":");
            groupStudents.forEach(System.out::println);
        });

        System.out.println("\nCel mai bun student:");
        Optional<Student> bestStudent = students.stream()
                .max(Comparator.comparing(Student::getGrade));

        bestStudent.ifPresent(System.out::println);

        System.out.println("\nExista cel putin un student picat?");
        boolean hasFailedStudent = students.stream()
                .anyMatch(student -> student.getGrade() < 5);

        System.out.println(hasFailedStudent);

        System.out.println("\nAu promovat toti studentii?");
        boolean allPassed = students.stream()
                .allMatch(Student::isPassed);

        System.out.println(allPassed);

        System.out.println("\nPrimii 3 studenti cu nota peste 7:");
        students.stream()
                .filter(student -> student.getGrade() > 7)
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\nNote distincte:");
        students.stream()
                .map(Student::getGrade)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println("\nNumar studenti bursieri:");
        long scholarshipCount = students.stream()
                .filter(Student::hasScholarship)
                .count();

        System.out.println(scholarshipCount);

        System.out.println("\nToate cursurile si studentii lor folosind flatMap:");
        List<Course> courses = StudentService.generateCourses();

        courses.stream()
                .flatMap(currentCourse -> currentCourse.getStudents().stream())
                .forEach(System.out::println);

        System.out.println("\nMedia pe fiecare curs:");
        courses.forEach(currentCourse ->
                System.out.println(currentCourse.getName() + " -> " + currentCourse.getAverageGrade())
        );

        System.out.println("\nParallel Stream:");
        students.parallelStream()
                .filter(Student::isPassed)
                .forEach(student ->
                        System.out.println(Thread.currentThread().getName() + " -> " + student.getName())
                );
    }
}