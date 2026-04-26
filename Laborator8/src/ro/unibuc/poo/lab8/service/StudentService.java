package ro.unibuc.poo.lab8.service;

import ro.unibuc.poo.lab8.model.Course;
import ro.unibuc.poo.lab8.model.Student;

import java.util.List;

public class StudentService {

    public static List<Student> generateStudents() {
        return List.of(
                new Student("Ana", 10, "A", true),
                new Student("Mihai", 7, "B", false),
                new Student("Ioana", 9, "A", true),
                new Student("Vlad", 4, "C", false),
                new Student("Maria", 8, "B", true),
                new Student("George", 5, "C", false),
                new Student("Elena", 3, "A", false),
                new Student("Andrei", 6, "B", false),
                new Student("Daria", 10, "C", true)
        );
    }

    public static Course generateJavaCourse() {
        Course course = new Course("Programare Orientata pe Obiecte II");

        generateStudents().forEach(course::addStudent);

        return course;
    }

    public static List<Course> generateCourses() {
        Course javaCourse = new Course("Programare Orientata pe Obiecte II");
        javaCourse.addStudent(new Student("Ana", 10, "A", true));
        javaCourse.addStudent(new Student("Mihai", 7, "B", false));
        javaCourse.addStudent(new Student("Ioana", 9, "A", true));

        Course algorithmsCourse = new Course("Algoritmi");
        algorithmsCourse.addStudent(new Student("Vlad", 4, "C", false));
        algorithmsCourse.addStudent(new Student("Maria", 8, "B", true));
        algorithmsCourse.addStudent(new Student("George", 5, "C", false));

        Course databasesCourse = new Course("Baze de date");
        databasesCourse.addStudent(new Student("Elena", 3, "A", false));
        databasesCourse.addStudent(new Student("Andrei", 6, "B", false));
        databasesCourse.addStudent(new Student("Daria", 10, "C", true));

        return List.of(javaCourse, algorithmsCourse, databasesCourse);
    }

    public static void printStudent(Student student) {
        System.out.println(student);
    }

    public static boolean isExcellent(Student student) {
        return student.getGrade() >= 9;
    }
}