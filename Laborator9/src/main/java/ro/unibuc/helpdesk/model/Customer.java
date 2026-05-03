package ro.unibuc.helpdesk.model;

public class Customer extends User implements Printable {

    public Customer(int id, String name, String email) {
        super(id, name, email);
    }

    public Customer(String name, String email) {
        super(name, email);
    }

    @Override
    public String printDetails() {
        return "Customer: " + id + " | " + name + " | " + email;
    }
}