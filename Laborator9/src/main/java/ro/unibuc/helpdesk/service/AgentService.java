package ro.unibuc.helpdesk.service;

import ro.unibuc.helpdesk.model.Agent;
import ro.unibuc.helpdesk.repository.AgentRepository;

import java.util.List;

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
}