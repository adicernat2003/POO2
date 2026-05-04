package ro.unibuc.helpdesk.service;

import java.util.List;

import ro.unibuc.helpdesk.exception.EntityNotFoundException;
import ro.unibuc.helpdesk.exception.InvalidStateException;
import ro.unibuc.helpdesk.model.Priority;
import ro.unibuc.helpdesk.model.Ticket;
import ro.unibuc.helpdesk.model.TicketStatus;
import ro.unibuc.helpdesk.repository.AgentRepository;
import ro.unibuc.helpdesk.repository.CategoryRepository;
import ro.unibuc.helpdesk.repository.CustomerRepository;
import ro.unibuc.helpdesk.repository.TicketRepository;

public class TicketService {

    private final TicketRepository repository;

    private final CustomerRepository customerRepository;

    private final CategoryRepository categoryRepository;

    private final AgentRepository agentRepository;

    private final AuditService audit;

    public TicketService(TicketRepository repository,
                         CustomerRepository customerRepository,
                         CategoryRepository categoryRepository, AgentRepository agentRepository,
                         AuditService audit) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
        this.agentRepository = agentRepository;
        this.audit = audit;
    }

    public void createTicket(String title, String description, Priority priority,
                             int customerId, int categoryId) {
        if (customerRepository.findById(customerId) == null) {
            throw new EntityNotFoundException(String.format("Customer with id %s not found.", customerId));
        }

        if (categoryRepository.findById(categoryId) == null) {
            throw new EntityNotFoundException(String.format("Category with id %s not found.", categoryId));
        }

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

    public void updateStatus(int ticketId, TicketStatus newStatus) {
        Ticket ticket = repository.findById(ticketId);

        if (ticket == null) {
            throw new EntityNotFoundException(String.format("Ticket with id %s not found.", ticketId));
        }

        if (ticket.getStatus() == newStatus) {
            throw new InvalidStateException("Ticket already has status: " + newStatus);
        }

        repository.updateStatus(ticketId, newStatus);
        audit.log("UPDATE_TICKET_STATUS");
    }

    public void assignAgent(int ticketId, int agentId) {
        Ticket ticket = repository.findById(ticketId);

        if (ticket == null) {
            throw new EntityNotFoundException(
                    String.format("Ticket with id %s not found.", ticketId)
            );
        }

        if (agentRepository.findById(agentId) == null) {
            throw new EntityNotFoundException(
                    String.format("Agent with id %s not found.", agentId)
            );
        }

        if (ticket.getAgentId() != null && ticket.getAgentId().equals(agentId)) {
            throw new InvalidStateException(
                    String.format("Ticket %s is already assigned to agent %s", ticketId, agentId)
            );
        }

        repository.assignAgent(ticketId, agentId);
        audit.log("ASSIGN_AGENT_TO_TICKET");
    }

    public void deleteTicket(int ticketId) {
        Ticket ticket = repository.findById(ticketId);

        if (ticket == null) {
            throw new EntityNotFoundException(
                    String.format("Ticket with id %s not found.", ticketId)
            );
        }

        repository.delete(ticketId);
        audit.log("DELETE_TICKET");
    }
}
