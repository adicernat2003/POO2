package lab.model;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Numele nu poate fi gol.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Varsta trebuie sa fie intre 0 si 120.");
        }
        this.age = age;
    }

    public void introduce() {
        System.out.println("Salut! Ma numesc " + name + " si am " + age + " ani.");
    }
}