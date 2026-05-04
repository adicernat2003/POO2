package ro.unibuc.helpdesk.model;

public class Ticket implements Printable {

    private int id;

    private String title;

    private String description;

    private TicketStatus status;

    private Priority priority;

    private int customerId;

    private Integer agentId;

    private int categoryId;

    public Ticket(int id, String title, String description,
                  TicketStatus status, Priority priority,
                  int customerId, Integer agentId, int categoryId) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.customerId = customerId;
        this.agentId = agentId;
        this.categoryId = categoryId;
    }

    public Ticket(String title, String description, Priority priority,
                  int customerId, int categoryId) {

        this(0, title, description, TicketStatus.OPEN,
                priority, customerId, null, categoryId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public String printDetails() {
        return "Ticket: " + id +
                " | " + title +
                " | " + status +
                " | " + priority;
    }
}
