package ro.unibuc.lab.model;

public record Student(String nume, int grupa, double medie) {

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", grupa=" + grupa +
                ", medie=" + medie +
                '}';
    }
}