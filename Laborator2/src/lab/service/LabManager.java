package lab.service;

import lab.inheritance.GraduateStudent;
import lab.model.Course;
import lab.model.Laboratory;
import lab.model.PackageInspector;
import lab.model.Student;
import lab.util.ArrayUtils;

public class LabManager {
    public void runDemo() {
        System.out.println("=== Demo laborator Java SE - POO ===");

        // clase si obiecte + constructori
        Student s1 = new Student();
        Student s2 = new Student(1, "Ana Popescu");
        Student s3 = new Student(2, "Mihai Ionescu", 2);

        // atribute + comportamente
        s1.setName("Student Default");
        s1.addGrade(0, 8.0);
        s1.addGrade(1, 9.0);

        s2.addGrade(0, 10.0);
        s2.addGrade(1, 9.5);
        s2.promoteYear();

        s3.addGrade(0, 7.0);
        s3.addGrade(1, 8.0);
        s3.addGrade(2, 9.0);

        s1.printStatus();
        s2.printStatus();
        s3.printStatus();

        // access modifier default prin clasa din acelasi pachet
        PackageInspector inspector = new PackageInspector();
        inspector.moveStudentToGroup(s3, "B2");
        System.out.println("Grupa lui " + s3.getName() + " dupa mutare: " + s3.getGroupCode());

        // mostenire + protected
        GraduateStudent graduateStudent = new GraduateStudent(3, "Ioana Marin", 1, "Analiza pattern-urilor OOP");
        graduateStudent.advanceResearchYear();
        graduateStudent.printResearchStatus();

        // arrays de primitive
        int[] labNumbers = {401, 402, 403, 404};
        System.out.print("\nArray de sali: ");
        ArrayUtils.printIntArray(labNumbers);

        // array de obiecte prin Course
        Course course = new Course("POO2", "Programare Avansata pe Obiecte in Java");
        course.enroll(s1);
        course.enroll(s2);
        course.enroll(s3);
        course.enroll(graduateStudent);
        course.printEnrolledStudentNames();

        // array multidimensional
        Laboratory laboratory = new Laboratory("L-405");
        laboratory.printSeating();
        System.out.println("Locul de pe randul 1, coloana 2 este: " + laboratory.getSeatFromRowColumn(1, 2));

        // metode si atribute de clasa
        System.out.println("\nUniversitate: " + Student.UNIVERSITY);
        System.out.println("Numar total studenti creati: " + Student.getTotalStudents());
        System.out.println("Numar total cursuri create: " + Course.getTotalCourses());
        System.out.println("Capacitate maxima curs: " + Course.MAX_STUDENTS);

        // metoda de clasa din utilitar
        double averageS3 = ArrayUtils.average(s3.getGrades());
        System.out.println("Media calculata static pentru " + s3.getName() + " = " + averageS3);
    }
}
