package ro.unibuc.poo.lab8.functional;

import ro.unibuc.poo.lab8.model.Student;

@FunctionalInterface
public interface StudentTransformer {

    String transform(Student student);
}