package ro.unibuc.lab7.model;

import ro.unibuc.lab7.enums.CourseType;

import java.util.Objects;

public record Course(String code, String title, CourseType type) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Course{code='%s', title='%s', type=%s}".formatted(code, title, type);
    }
}