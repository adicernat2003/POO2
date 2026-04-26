package ro.unibuc.poo.lab8.model;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private final String name;
    private final List<Student> students = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public double getAverageGrade() {
        return students.stream()
                .mapToInt(Student::getGrade)
                .average()
                .orElse(0);
    }

    public List<Student> getPassedStudents() {
        return students.stream()
                .filter(Student::isPassed)
                .toList();
    }

    public List<Student> getScholarshipStudents() {
        return students.stream()
                .filter(Student::hasScholarship)
                .toList();
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", students=" + students.size() +
                '}';
    }
}