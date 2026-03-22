package laborator.model;

public class Professor extends Person {
    private String academicTitle;
    private double salary;

    public Professor(String id, String firstName, String lastName, String email,
                     String academicTitle, double salary) {
        super(id, firstName, lastName, email);

        if (academicTitle == null || academicTitle.isBlank()) {
            throw new IllegalArgumentException("Titlul academic nu poate fi gol.");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salariul nu poate fi negativ.");
        }

        this.academicTitle = academicTitle;
        this.salary = salary;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        if (academicTitle == null || academicTitle.isBlank()) {
            throw new IllegalArgumentException("Titlul academic nu poate fi gol.");
        }
        this.academicTitle = academicTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salariul nu poate fi negativ.");
        }
        this.salary = salary;
    }

    @Override
    public String getRole() {
        return "Professor";
    }
}
