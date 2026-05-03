package ro.unibuc.helpdesk.service;

import ro.unibuc.helpdesk.model.Comment;
import ro.unibuc.helpdesk.repository.CommentRepository;

import java.util.List;

public class CommentService {

    private final CommentRepository repository;
    private final AuditService audit;

    public CommentService(CommentRepository repository, AuditService audit) {
        this.repository = repository;
        this.audit = audit;
    }

    public void addComment(int ticketId, String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Comment message is required.");
        }

        repository.create(new Comment(ticketId, message));
        audit.log("ADD_COMMENT");
    }

    public List<Comment> getCommentsForTicket(int ticketId) {
        audit.log("LIST_COMMENTS_FOR_TICKET");
        return repository.findByTicketId(ticketId);
    }
}