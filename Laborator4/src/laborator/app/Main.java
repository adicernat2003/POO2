package laborator.app;

import laborator.immutable.ImmutableStudentProfile;
import laborator.model.Course;
import laborator.model.Department;
import laborator.model.Professor;
import laborator.model.Student;
import laborator.model.Syllabus;
import laborator.record.GradeRecord;
import laborator.util.TextProcessor;

public class Main {
    public static void main(String[] args) {

        Professor professor = new Professor(
                "P100",
                "Elena",
                "Popescu",
                "elena.popescu@univ.ro",
                "Conferentiar",
                9500
        );

        Student student = new Student(
                "S101",
                "Andrei",
                "Ionescu",
                "andrei.ionescu@stud.univ.ro",
                2,
                850
        );

        Department department = new Department(
                "Informatica",
                "Corpul A",
                "021-123-4567"
        );

        String[] topics = {
                "Clase si obiecte",
                "Mostenire",
                "Compozitie",
                "String-uri"
        };

        Syllabus syllabus = new Syllabus(
                "Disciplina dedicata programarii orientate pe obiecte",
                topics
        );

        Course course = new Course(
                "PAO204",
                "Programare Avansata pe Obiecte",
                professor,
                syllabus
        );

        ImmutableStudentProfile profile = new ImmutableStudentProfile(
                "2025-RO-001",
                student.getFullName(),
                department,
                9.45
        );

        GradeRecord gradeRecord = new GradeRecord(
                student.getId(),
                course.getCode(),
                9.80
        );

        System.out.println("=== MOSTENIRE ===");
        System.out.println(student);
        System.out.println(professor);
        System.out.println(student.getRole());
        System.out.println(professor.getRole());

        System.out.println("\n=== OBJECT: equals, hashCode, toString, getClass ===");
        Student anotherStudentWithSameId = new Student(
                "S101",
                "Andrei",
                "Georgescu",
                "alt.email@stud.univ.ro",
                3,
                1000
        );

        System.out.println("student.equals(anotherStudentWithSameId): " + student.equals(anotherStudentWithSameId));
        System.out.println("student.hashCode(): " + student.hashCode());
        System.out.println("student.toString(): " + student);
        System.out.println("student.getClass(): " + student.getClass().getName());

        System.out.println("\n=== AGREGARE VS COMPOZITIE ===");
        System.out.println("Profesor inainte de modificare: " + course.getCoordinatorName());
        professor.setLastName("Marinescu");
        System.out.println("Profesor dupa modificare externa: " + course.getCoordinatorName());

        System.out.println("\nSyllabus original inainte de modificare externa: " + course.getSyllabus());
        syllabus.addTopic("Threading");
        System.out.println("Syllabus extern modificat: " + syllabus);
        System.out.println("Syllabus din curs (ramane protejat prin compozitie): " + course.getSyllabus());

        System.out.println("\n=== IMUTABILITATE ===");
        System.out.println(profile);
        Department copiedDepartment = profile.getDepartment();
        copiedDepartment.setName("Matematica");
        System.out.println("Departament modificat prin copia returnata: " + copiedDepartment);
        System.out.println("Obiectul imutabil ramane neschimbat: " + profile);

        System.out.println("\n=== RECORD ===");
        System.out.println(gradeRecord);
        System.out.println("studentId: " + gradeRecord.studentId());
        System.out.println("courseCode: " + gradeRecord.courseCode());
        System.out.println("isPromoted: " + gradeRecord.isPromoted());

        System.out.println("\n=== STRING ===");
        System.out.println("compare literal strings (equals): " + TextProcessor.compareLiteralStrings());
        System.out.println("compare heap strings (equals): " + TextProcessor.compareHeapStrings());
        System.out.println(TextProcessor.describeStringMethods("   Java este foarte utila in POO.   "));
        System.out.println("Imutabilitate String: " + TextProcessor.demonstrateStringImmutability("catalog"));
        System.out.println("Cuvinte extrase: " + TextProcessor.arrayToString("Java, POO, mostenire si compozitie!"));

        System.out.println("\n=== STRINGBUILDER ===");
        System.out.println(TextProcessor.buildStudentReportWithStringBuilder(student, course, gradeRecord));

        System.out.println("\n=== STRINGBUFFER ===");
        System.out.println(TextProcessor.buildThreadSafeLog("SAVE_GRADE", "Nota salvata pentru " + student.getId()));

        System.out.println("\n=== TOPICS ===");
        System.out.println(TextProcessor.joinTopics(course.getSyllabus()));
    }
}