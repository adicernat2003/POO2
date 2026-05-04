package ro.unibuc.helpdesk.model;

public class Comment implements Printable {

    private int id;

    private int ticketId;

    private String message;

    public Comment(int id, int ticketId, String message) {
        this.id = id;
        this.ticketId = ticketId;
        this.message = message;
    }

    public Comment(int ticketId, String message) {
        this(0, ticketId, message);
    }

    public int getId() {
        return id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String printDetails() {
        return "Comment: " + id +
                " | Ticket " + ticketId +
                " | " + message;
    }
}
