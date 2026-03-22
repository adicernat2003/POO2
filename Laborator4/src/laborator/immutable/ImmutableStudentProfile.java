package laborator.immutable;

import laborator.model.Department;

public final class ImmutableStudentProfile {
    private final String registrationNumber;
    private final String fullName;
    private final Department department;
    private final double averageGrade;

    public ImmutableStudentProfile(String registrationNumber,
                                   String fullName,
                                   Department department,
                                   double averageGrade) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Numarul matricol nu poate fi gol.");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Numele complet nu poate fi gol.");
        }
        if (department == null) {
            throw new IllegalArgumentException("Departamentul nu poate fi null.");
        }
        if (averageGrade < 0 || averageGrade > 10) {
            throw new IllegalArgumentException("Media trebuie sa fie intre 0 si 10.");
        }

        this.registrationNumber = registrationNumber;
        this.fullName = fullName;
        this.department = new Department(department); // copie defensiva
        this.averageGrade = averageGrade;
    }

//    public void setRegistrationNumber(String registrationNumber) {
//        this.registrationNumber = registrationNumber;
//    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public Department getDepartment() {
        return new Department(department); // copie defensiva
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public String toString() {
        return "ImmutableStudentProfile{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department=" + department +
                ", averageGrade=" + averageGrade +
                '}';
    }
}