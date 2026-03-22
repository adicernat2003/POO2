package laborator.record;

public record GradeRecord(String studentId, String courseCode, double grade) {

    public GradeRecord {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("studentId nu poate fi gol.");
        }
        if (courseCode == null || courseCode.isBlank()) {
            throw new IllegalArgumentException("courseCode nu poate fi gol.");
        }
        if (grade < 0 || grade > 10) {
            throw new IllegalArgumentException("Nota trebuie sa fie intre 0 si 10.");
        }
    }

    public boolean isPromoted() {
        return grade >= 5.0;
    }

//    public void setGrade(double grade) {
//        this.grade = grade;
//    }
}
