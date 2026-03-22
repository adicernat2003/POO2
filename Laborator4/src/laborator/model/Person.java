package laborator.model;

public abstract class Person {
    private final String id;
    private String firstName;
    private String lastName;
    private String email;

    protected Person(String id, String firstName, String lastName, String email) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID-ul nu poate fi gol.");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Prenumele nu poate fi gol.");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Numele nu poate fi gol.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email-ul nu poate fi gol.");
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public final String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Prenumele nu poate fi gol.");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Numele nu poate fi gol.");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email-ul nu poate fi gol.");
        }
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public abstract String getRole();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}