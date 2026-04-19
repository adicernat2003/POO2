package ro.unibuc.lab7.enums;

public enum CourseType {
    MANDATORY("Curs obligatoriu"),
    OPTIONAL("Curs optional"),
    LABORATORY("Laborator");

    private final String description;

    private CourseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}