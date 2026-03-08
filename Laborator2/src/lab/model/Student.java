package lab.model;

import lab.util.ArrayUtils;

/**
 * Clasa care demonstreaza:
 * - atribute de instanta
 * - constante final
 * - atribute de clasa static
 * - constructori supraincarcati
 * - this() si this
 * - comportamente (metode de instanta)
 * - modificatori de acces
 */
public class Student {
    // zona de memorie de clasa
    private static int totalStudents = 0;

    // zona de memorie constanta: toate obiectele au aceeasi valoare
    public static final String UNIVERSITY = "Universitatea din Bucuresti";

    private final int studentId;      // constanta per obiect
    private String name;              // atribut privat
    protected int yearOfStudy;        // accesibil in subclase
    String groupCode;                 // default/package-private
    private double[] grades;          // array de note

    // bloc de initializare: ruleaza inaintea constructorului
    {
        grades = new double[5];
        groupCode = "B1";
    }

    // constructor default
    public Student() {
        this(0, "Anonim", 1);
    }

    // constructor cu 2 parametri
    public Student(int studentId, String name) {
        this(studentId, name, 1);
    }

    // constructor principal
    public Student(int studentId, String name, int yearOfStudy) {
        this.studentId = studentId;
        this.name = name;
        this.yearOfStudy = yearOfStudy;
        totalStudents++;
    }

    // comportament: modifica starea obiectului
    public void addGrade(int index, double value) {
        if (index >= 0 && index < grades.length) {
            this.grades[index] = value;
        }
    }

    public void promoteYear() {
        this.yearOfStudy++;
    }

    public double calculateAverage() {
        return ArrayUtils.average(grades);
    }

    public void printStatus() {
        System.out.println("Student " + name + " | grupa=" + groupCode +
                " | an=" + yearOfStudy + " | medie=" + calculateAverage());
    }

    // metoda de clasa
    public static int getTotalStudents() {
        return totalStudents;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public double[] getGrades() {
        return grades;
    }

    // metoda default - accesibila doar in acelasi pachet
    void changeGroup(String newGroupCode) {
        this.groupCode = newGroupCode;
    }
}
