package ro.unibuc.poo.lab8.functional;

import ro.unibuc.poo.lab8.model.Student;

@FunctionalInterface
public interface GradeEvaluator {

    boolean evaluate(Student student);
}