package ro.unibuc.lab.repository;

import ro.unibuc.lab.exception.InvalidStudentDataException;

public class StudentRepository {

    public void saveStudent(String name, int age) {
        validate(name, age);

        System.out.println("Studentul a fost salvat: " + name + ", varsta " + age);
    }

    private void validate(String name, int age) {
        if (name == null || name.isBlank()) {
            throw new InvalidStudentDataException("Numele studentului nu poate fi gol.");
        }

        if (age < 18) {
            throw new InvalidStudentDataException("Varsta minima trebuie sa fie 18.");
        }
    }
}
