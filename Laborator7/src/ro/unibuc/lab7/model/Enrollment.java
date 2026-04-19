package ro.unibuc.lab7.model;

public record Enrollment(Student student, Course course, Double grade) {

    @Override
    public String toString() {
        return "Enrollment{student=%s, course=%s, grade=%.2f}"
                .formatted(student.name(), course.title(), grade);
    }
}