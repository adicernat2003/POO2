package ro.unibuc.lab7.service;

import ro.unibuc.lab7.model.Course;
import ro.unibuc.lab7.model.Enrollment;
import ro.unibuc.lab7.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniversityService {

    private final List<Student> students = new ArrayList<>();
    private final Set<Course> courses = new LinkedHashSet<>();
    private final Map<String, List<Enrollment>> enrollmentsByStudentId = new HashMap<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudent(Student student, Course course, Double grade) {
        Enrollment enrollment = new Enrollment(student, course, grade);

        enrollmentsByStudentId
                .computeIfAbsent(student.id(), _ -> new ArrayList<>())
                .add(enrollment);
    }

    public double calculateAverageGrade(String studentId) {
        List<Enrollment> enrollments = getEnrollmentsForStudent(studentId);
        if (enrollments.isEmpty()) {
            return 0;
        }

        double sum = 0;
        for (Enrollment enrollment : enrollments) {
            sum += enrollment.grade();
        }

        return sum / enrollments.size();
    }

    public void printAllData() {
        System.out.println("=== STUDENTS ===");
        students.forEach(student -> System.out.println(student));

        System.out.println("\n=== COURSES ===");
        courses.forEach(System.out::println);

        System.out.println("\n=== ENROLLMENTS ===");
        for (Map.Entry<String, List<Enrollment>> entry : enrollmentsByStudentId.entrySet()) {
            System.out.println("Student ID: " + entry.getKey());
            for (Enrollment enrollment : entry.getValue()) {
                System.out.println("  " + enrollment);
            }
        }
    }

    private List<Enrollment> getEnrollmentsForStudent(String studentId) {
        return enrollmentsByStudentId.getOrDefault(studentId, Collections.emptyList());
    }
}