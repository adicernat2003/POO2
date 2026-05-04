package ro.unibuc.helpdesk.service;

import java.util.List;

import ro.unibuc.helpdesk.exception.EntityNotFoundException;
import ro.unibuc.helpdesk.model.Comment;
import ro.unibuc.helpdesk.repository.CommentRepository;
import ro.unibuc.helpdesk.repository.TicketRepository;

public class CommentService {

    private final CommentRepository repository;

    private final TicketRepository ticketRepository;

    private final AuditService audit;

    public CommentService(CommentRepository repository,
                          TicketRepository ticketRepository,
                          AuditService audit) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.audit = audit;
    }

    public void addComment(int ticketId, String message) {
        if (ticketRepository.findById(ticketId) == null) {
            throw new EntityNotFoundException(
                    String.format("Ticket with id %s not found", ticketId)
            );
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Comment message is required.");
        }

        repository.create(new Comment(ticketId, message));
        audit.log("ADD_COMMENT");
    }

    public List<Comment> getCommentsForTicket(int ticketId) {
        if (ticketRepository.findById(ticketId) == null) {
            throw new EntityNotFoundException(
                    String.format("Ticket with id %s not found.", ticketId)
            );
        }

        audit.log("LIST_COMMENTS_FOR_TICKET");
        return repository.findByTicketId(ticketId);
    }
}
