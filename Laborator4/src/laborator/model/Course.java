package laborator.model;

public class Course {
    private final String code;
    private String title;

    // Agregare: profesorul exista independent de curs
    private Professor coordinator;

    // Compozitie: syllabus-ul este copiat si controlat de Course
    private Syllabus syllabus;

    public Course(String code, String title, Professor coordinator, Syllabus syllabus) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Codul cursului nu poate fi gol.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Titlul cursului nu poate fi gol.");
        }
        if (coordinator == null) {
            throw new IllegalArgumentException("Profesorul coordonator nu poate fi null.");
        }
        if (syllabus == null) {
            throw new IllegalArgumentException("Syllabus-ul nu poate fi null.");
        }

        this.code = code;
        this.title = title;
        this.coordinator = coordinator;          // agregare
        this.syllabus = new Syllabus(syllabus);  // compozitie
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Titlul cursului nu poate fi gol.");
        }
        this.title = title;
    }

    public Professor getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Professor coordinator) {
        if (coordinator == null) {
            throw new IllegalArgumentException("Profesorul coordonator nu poate fi null.");
        }
        this.coordinator = coordinator;
    }

    public Syllabus getSyllabus() {
        return new Syllabus(syllabus);
    }

    public void updateSyllabus(Syllabus newSyllabus) {
        if (newSyllabus == null) {
            throw new IllegalArgumentException("Syllabus-ul nou nu poate fi null.");
        }
        this.syllabus = new Syllabus(newSyllabus);
    }

    public String getCoordinatorName() {
        return coordinator.getFullName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course other)) return false;
        return code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", coordinator='" + coordinator.getFullName() + '\'' +
                ", syllabus=" + syllabus +
                '}';
    }
}
