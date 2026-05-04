package ro.unibuc.helpdesk.service;

import java.util.List;

import ro.unibuc.helpdesk.exception.EntityNotFoundException;
import ro.unibuc.helpdesk.model.Agent;
import ro.unibuc.helpdesk.repository.AgentRepository;

public class AgentService {

    private final AgentRepository repository;

    private final AuditService audit;

    public AgentService(AgentRepository repository, AuditService audit) {
        this.repository = repository;
        this.audit = audit;
    }

    public void addAgent(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required.");
        }

        repository.create(new Agent(name, email));
        audit.log("ADD_AGENT");
    }

    public List<Agent> getAllAgents() {
        audit.log("LIST_AGENTS");
        return repository.findAll();
    }

    public void deleteAgent(int id) {
        if (repository.findById(id) == null) {
            throw new EntityNotFoundException(
                    String.format("Agent with id %s not found.", id)
            );
        }

        repository.delete(id);
        audit.log("DELETE_AGENT");
    }
}
