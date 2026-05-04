package ro.unibuc.helpdesk.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ro.unibuc.helpdesk.exception.EntityNotFoundException;
import ro.unibuc.helpdesk.exception.InvalidStateException;
import ro.unibuc.helpdesk.model.Agent;
import ro.unibuc.helpdesk.model.Category;
import ro.unibuc.helpdesk.model.Customer;
import ro.unibuc.helpdesk.model.Priority;
import ro.unibuc.helpdesk.model.Ticket;
import ro.unibuc.helpdesk.model.TicketStatus;
import ro.unibuc.helpdesk.repository.AgentRepository;
import ro.unibuc.helpdesk.repository.CategoryRepository;
import ro.unibuc.helpdesk.repository.CustomerRepository;
import ro.unibuc.helpdesk.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository repository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private AgentRepository agentRepository;

    @Mock
    private AuditService audit;

    @InjectMocks
    private TicketService service;

    @Test
    void createTicketShouldCreateTicketAndAuditWhenDataIsValid() {
        when(customerRepository.findById(1))
                .thenReturn(new Customer(1, "Ana", "ana@test.com"));

        when(categoryRepository.findById(2))
                .thenReturn(new Category(2, "Software"));

        service.createTicket("Login issue", "Cannot login", Priority.HIGH, 1, 2);

        ArgumentCaptor<Ticket> ticketCaptor = ArgumentCaptor.forClass(Ticket.class);
        verify(repository).create(ticketCaptor.capture());

        Ticket ticket = ticketCaptor.getValue();

        assertEquals("Login issue", ticket.getTitle());
        assertEquals("Cannot login", ticket.getDescription());
        assertEquals(Priority.HIGH, ticket.getPriority());
        assertEquals(TicketStatus.OPEN, ticket.getStatus());
        assertEquals(1, ticket.getCustomerId());
        assertEquals(2, ticket.getCategoryId());

        verify(audit).log("CREATE_TICKET");
    }

    @Test
    void createTicketShouldThrowWhenCustomerDoesNotExist() {
        when(customerRepository.findById(10)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                service.createTicket("Title", "Description", Priority.LOW, 10, 1)
        );

        verifyNoInteractions(categoryRepository);
        verifyNoInteractions(repository);
        verifyNoInteractions(audit);
    }

    @Test
    void createTicketShouldThrowWhenCategoryDoesNotExist() {
        when(customerRepository.findById(1))
                .thenReturn(new Customer(1, "Ana", "ana@test.com"));

        when(categoryRepository.findById(20)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                service.createTicket("Title", "Description", Priority.LOW, 1, 20)
        );

        verify(repository, never()).create(any());
        verifyNoInteractions(audit);
    }

    @Test
    void createTicketShouldThrowWhenTitleIsNull() {
        when(customerRepository.findById(1))
                .thenReturn(new Customer(1, "Ana", "ana@test.com"));

        when(categoryRepository.findById(2))
                .thenReturn(new Category(2, "Software"));

        assertThrows(IllegalArgumentException.class, () ->
                service.createTicket(null, "Description", Priority.LOW, 1, 2)
        );

        verify(repository, never()).create(any());
        verifyNoInteractions(audit);
    }

    @Test
    void createTicketShouldThrowWhenTitleIsBlank() {
        when(customerRepository.findById(1))
                .thenReturn(new Customer(1, "Ana", "ana@test.com"));

        when(categoryRepository.findById(2))
                .thenReturn(new Category(2, "Software"));

        assertThrows(IllegalArgumentException.class, () ->
                service.createTicket("", "Description", Priority.LOW, 1, 2)
        );

        verify(repository, never()).create(any());
        verifyNoInteractions(audit);
    }

    @Test
    void createTicketShouldThrowWhenDescriptionIsNull() {
        when(customerRepository.findById(1))
                .thenReturn(new Customer(1, "Ana", "ana@test.com"));

        when(categoryRepository.findById(2))
                .thenReturn(new Category(2, "Software"));

        assertThrows(IllegalArgumentException.class, () ->
                service.createTicket("Title", null, Priority.LOW, 1, 2)
        );

        verify(repository, never()).create(any());
        verifyNoInteractions(audit);
    }

    @Test
    void createTicketShouldThrowWhenDescriptionIsBlank() {
        when(customerRepository.findById(1))
                .thenReturn(new Customer(1, "Ana", "ana@test.com"));

        when(categoryRepository.findById(2))
                .thenReturn(new Category(2, "Software"));

        assertThrows(IllegalArgumentException.class, () ->
                service.createTicket("Title", "", Priority.LOW, 1, 2)
        );

        verify(repository, never()).create(any());
        verifyNoInteractions(audit);
    }

    @Test
    void getAllTicketsShouldReturnTicketsAndAudit() {
        List<Ticket> tickets = List.of(
                new Ticket(1, "T1", "D1", TicketStatus.OPEN, Priority.LOW, 1, null, 1),
                new Ticket(2, "T2", "D2", TicketStatus.CLOSED, Priority.HIGH, 1, 3, 1)
        );

        when(repository.findAll()).thenReturn(tickets);

        List<Ticket> result = service.getAllTickets();

        assertEquals(2, result.size());
        assertEquals("T1", result.get(0).getTitle());
        assertEquals("T2", result.get(1).getTitle());

        verify(repository).findAll();
        verify(audit).log("LIST_TICKETS");
    }

    @Test
    void updateStatusShouldUpdateStatusAndAuditWhenValid() {
        Ticket existingTicket = new Ticket(
                1,
                "Login issue",
                "Cannot login",
                TicketStatus.OPEN,
                Priority.HIGH,
                1,
                null,
                2
        );

        when(repository.findById(1)).thenReturn(existingTicket);

        service.updateStatus(1, TicketStatus.IN_PROGRESS);

        verify(repository).updateStatus(1, TicketStatus.IN_PROGRESS);
        verify(audit).log("UPDATE_TICKET_STATUS");
    }

    @Test
    void updateStatusShouldThrowWhenTicketDoesNotExist() {
        when(repository.findById(99)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                service.updateStatus(99, TicketStatus.CLOSED)
        );

        verify(repository, never()).updateStatus(anyInt(), any());
        verifyNoInteractions(audit);
    }

    @Test
    void updateStatusShouldThrowWhenStatusIsTheSame() {
        Ticket existingTicket = new Ticket(
                1,
                "Login issue",
                "Cannot login",
                TicketStatus.OPEN,
                Priority.HIGH,
                1,
                null,
                2
        );

        when(repository.findById(1)).thenReturn(existingTicket);

        assertThrows(InvalidStateException.class, () ->
                service.updateStatus(1, TicketStatus.OPEN)
        );

        verify(repository, never()).updateStatus(anyInt(), any());
        verifyNoInteractions(audit);
    }

    @Test
    void assignAgentShouldAssignAgentAndAuditWhenTicketAndAgentExist() {
        Ticket ticket = new Ticket(
                1,
                "Login issue",
                "Cannot login",
                TicketStatus.OPEN,
                Priority.HIGH,
                1,
                null,
                2
        );

        when(repository.findById(1)).thenReturn(ticket);
        when(agentRepository.findById(5)).thenReturn(new Agent(5, "Alex", "alex@test.com"));

        service.assignAgent(1, 5);

        verify(repository).assignAgent(1, 5);
        verify(audit).log("ASSIGN_AGENT_TO_TICKET");
    }

    @Test
    void assignAgentShouldAllowReassigningTicketToDifferentAgent() {
        Ticket ticket = new Ticket(
                1,
                "Login issue",
                "Cannot login",
                TicketStatus.OPEN,
                Priority.HIGH,
                1,
                5,
                2
        );

        when(repository.findById(1)).thenReturn(ticket);
        when(agentRepository.findById(7)).thenReturn(new Agent(7, "Maria", "maria@test.com"));

        service.assignAgent(1, 7);

        verify(repository).assignAgent(1, 7);
        verify(audit).log("ASSIGN_AGENT_TO_TICKET");
    }

    @Test
    void assignAgentShouldThrowWhenTicketDoesNotExist() {
        when(repository.findById(99)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                service.assignAgent(99, 5)
        );

        verify(agentRepository, never()).findById(anyInt());
        verify(repository, never()).assignAgent(anyInt(), anyInt());
        verifyNoInteractions(audit);
    }

    @Test
    void assignAgentShouldThrowWhenAgentDoesNotExist() {
        Ticket ticket = new Ticket(
                1,
                "Login issue",
                "Cannot login",
                TicketStatus.OPEN,
                Priority.HIGH,
                1,
                null,
                2
        );

        when(repository.findById(1)).thenReturn(ticket);
        when(agentRepository.findById(5)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                service.assignAgent(1, 5)
        );

        verify(repository, never()).assignAgent(anyInt(), anyInt());
        verifyNoInteractions(audit);
    }

    @Test
    void assignAgentShouldThrowWhenTicketAlreadyAssignedToSameAgent() {
        Ticket ticket = new Ticket(
                1,
                "Login issue",
                "Cannot login",
                TicketStatus.OPEN,
                Priority.HIGH,
                1,
                5,
                2
        );

        when(repository.findById(1)).thenReturn(ticket);
        when(agentRepository.findById(5)).thenReturn(new Agent(5, "Alex", "alex@test.com"));

        assertThrows(InvalidStateException.class, () ->
                service.assignAgent(1, 5)
        );

        verify(repository, never()).assignAgent(anyInt(), anyInt());
        verifyNoInteractions(audit);
    }

    @Test
    void deleteTicketShouldThrowWhenTicketDoesNotExist() {
        when(repository.findById(99)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                service.deleteTicket(99)
        );

        verify(repository, never()).delete(anyInt());
        verifyNoInteractions(audit);
    }

    @Test
    void deleteTicketShouldDeleteTicketAndAuditWhenExists() {
        Ticket ticket = new Ticket(
                1,
                "Title",
                "Description",
                TicketStatus.OPEN,
                Priority.LOW,
                1,
                null,
                1
        );

        when(repository.findById(1)).thenReturn(ticket);

        service.deleteTicket(1);

        verify(repository).delete(1);
        verify(audit).log("DELETE_TICKET");
    }
}
