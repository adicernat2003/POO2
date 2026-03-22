package laborator.model;

public class Student extends Person {
    private int yearOfStudy;
    private double scholarship;

    public Student(String id, String firstName, String lastName, String email,
                   int yearOfStudy, double scholarship) {
        super(id, firstName, lastName, email);

        if (yearOfStudy < 1 || yearOfStudy > 6) {
            throw new IllegalArgumentException("Anul de studiu trebuie sa fie intre 1 si 6.");
        }
        if (scholarship < 0) {
            throw new IllegalArgumentException("Bursa nu poate fi negativa.");
        }

        this.yearOfStudy = yearOfStudy;
        this.scholarship = scholarship;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        if (yearOfStudy < 1 || yearOfStudy > 6) {
            throw new IllegalArgumentException("Anul de studiu trebuie sa fie intre 1 si 6.");
        }
        this.yearOfStudy = yearOfStudy;
    }

    public double getScholarship() {
        return scholarship;
    }

    public void setScholarship(double scholarship) {
        if (scholarship < 0) {
            throw new IllegalArgumentException("Bursa nu poate fi negativa.");
        }
        this.scholarship = scholarship;
    }

    @Override
    public String getRole() {
        return "Student";
    }
}