package ro.unibuc.poo.lab8.model;

public class Student {

    private final String name;
    private final int grade;
    private final String group;
    private final boolean scholarship;

    public Student(String name, int grade, String group, boolean scholarship) {
        this.name = name;
        this.grade = grade;
        this.group = group;
        this.scholarship = scholarship;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public String getGroup() {
        return group;
    }

    public boolean hasScholarship() {
        return scholarship;
    }

    public boolean isPassed() {
        return grade >= 5;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", group='" + group + '\'' +
                ", scholarship=" + scholarship +
                '}';
    }
}