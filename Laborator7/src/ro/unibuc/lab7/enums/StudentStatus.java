package ro.unibuc.lab7.enums;

public enum StudentStatus {
    ACTIVE("Student activ"),
    SUSPENDED("Student suspendat"),
    GRADUATED("Student absolvent");

    private final String description;

    private StudentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}