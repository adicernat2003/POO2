package ro.unibuc.lab7.model;

import ro.unibuc.lab7.enums.StudentStatus;

import java.util.Objects;

public record Student(String id, String name, Integer age, StudentStatus status) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{id='%s', name='%s', age=%d, status=%s}".formatted(id, name, age, status);
    }
}