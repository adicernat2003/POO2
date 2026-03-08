package lab.model;

import java.util.Arrays;

/**
 * Clasa pentru demonstrarea obiectelor, array-urilor de obiecte si atributelor static/final.
 */
public class Course {
    public static final int MAX_STUDENTS = 30; // constanta de clasa
    private static int totalCourses = 0;       // memorie de clasa

    private final String code;
    private String title;
    private Student[] enrolledStudents;
    private int enrolledCount;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
        this.enrolledStudents = new Student[MAX_STUDENTS];
        totalCourses++;
    }

    public void enroll(Student student) {
        if (enrolledCount >= enrolledStudents.length) {
            return; // nu mai putem inscrie studenti
        }
        enrolledStudents[enrolledCount++] = student;
    }

    public void printEnrolledStudentNames() {
        System.out.println("\nCurs: " + code + " - " + title);
        for (int i = 0; i < enrolledCount; i++) {
            System.out.println(" - " + enrolledStudents[i].getName());
        }
    }

    public Student[] getActiveStudents() {
        return Arrays.copyOf(enrolledStudents, enrolledCount);
    }

    public static int getTotalCourses() {
        return totalCourses;
    }
}
