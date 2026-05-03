package ro.unibuc.helpdesk.service;

import ro.unibuc.helpdesk.exception.EntityNotFoundException;
import ro.unibuc.helpdesk.model.Priority;
import ro.unibuc.helpdesk.model.Ticket;
import ro.unibuc.helpdesk.model.TicketStatus;
import ro.unibuc.helpdesk.repository.TicketRepository;

import java.util.List;

public class TicketService {

    private final TicketRepository repository;
    private final AuditService audit;

    public TicketService(TicketRepository repository, AuditService audit) {
        this.repository = repository;
        this.audit = audit;
    }

    public void createTicket(String title, String description, Priority priority,
                             int customerId, int categoryId) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required.");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required.");
        }

        Ticket ticket = new Ticket(title, description, priority, customerId, categoryId);
        repository.create(ticket);

        audit.log("CREATE_TICKET");
    }

    public List<Ticket> getAllTickets() {
        audit.log("LIST_TICKETS");
        return repository.findAll();
    }

    public void updateStatus(int ticketId, TicketStatus status) {
        Ticket ticket = repository.findById(ticketId);

        if (ticket == null) {
            throw new EntityNotFoundException("Ticket not found: " + ticketId);
        }

        repository.updateStatus(ticketId, status);
        audit.log("UPDATE_TICKET_STATUS");
    }

    public void assignAgent(int ticketId, int agentId) {
        repository.assignAgent(ticketId, agentId);
        audit.log("ASSIGN_AGENT_TO_TICKET");
    }
}