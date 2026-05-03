package ro.unibuc.helpdesk.model;

public class Agent extends User implements Printable {

    public Agent(int id, String name, String email) {
        super(id, name, email);
    }

    public Agent(String name, String email) {
        super(name, email);
    }

    @Override
    public String printDetails() {
        return "Agent: " + id + " | " + name + " | " + email;
    }
}