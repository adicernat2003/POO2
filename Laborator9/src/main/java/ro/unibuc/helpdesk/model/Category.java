package ro.unibuc.helpdesk.model;

public class Category implements Printable {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this(0, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String printDetails() {
        return "Category: " + id + " | " + name;
    }
}