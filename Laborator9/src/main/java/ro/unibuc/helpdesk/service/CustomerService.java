package ro.unibuc.helpdesk.service;

import java.util.List;

import ro.unibuc.helpdesk.exception.DeleteNotAllowedException;
import ro.unibuc.helpdesk.exception.EntityNotFoundException;
import ro.unibuc.helpdesk.exception.ValidationException;
import ro.unibuc.helpdesk.model.Customer;
import ro.unibuc.helpdesk.repository.CustomerRepository;
import ro.unibuc.helpdesk.repository.TicketRepository;

public class CustomerService {

    private final CustomerRepository repository;

    private final TicketRepository ticketRepository;

    private final AuditService audit;

    public CustomerService(CustomerRepository repository,
                           TicketRepository ticketRepository,
                           AuditService audit) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.audit = audit;
    }

    public void addCustomer(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name is required.");
        }

        if (email == null || email.isBlank()) {
            throw new ValidationException("Email is required.");
        }

        if (!email.contains("@")) {
            throw new ValidationException("Email is invalid.");
        }

        repository.create(new Customer(name, email));
        audit.log("ADD_CUSTOMER");
    }

    public List<Customer> getAllCustomers() {
        audit.log("LIST_CUSTOMERS");
        return repository.findAll();
    }

    public void deleteCustomer(int id) {
        if (id <= 0) {
            throw new ValidationException("Customer id is invalid.");
        }

        if (repository.findById(id) == null) {
            throw new EntityNotFoundException(String.format("Customer with id %s not found.", id));
        }

        if (ticketRepository.existsByCustomerId(id)) {
            throw new DeleteNotAllowedException(
                    "Customer cannot be deleted because it has tickets."
            );
        }

        repository.delete(id);
        audit.log("DELETE_CUSTOMER");
    }
}
