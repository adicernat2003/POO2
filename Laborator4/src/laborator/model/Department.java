package laborator.model;

public class Department {
    private String name;
    private String building;
    private String phone;

    public Department(String name, String building, String phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Numele departamentului nu poate fi gol.");
        }
        this.name = name;
        this.building = building;
        this.phone = phone;
    }

    public Department(Department other) {
        this.name = other.name;
        this.building = other.building;
        this.phone = other.phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Numele departamentului nu poate fi gol.");
        }
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", building='" + building + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
