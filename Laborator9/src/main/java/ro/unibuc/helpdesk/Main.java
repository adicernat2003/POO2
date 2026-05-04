package ro.unibuc.helpdesk;

import java.util.Scanner;

import ro.unibuc.helpdesk.config.DatabaseInitializer;
import ro.unibuc.helpdesk.model.Agent;
import ro.unibuc.helpdesk.model.Category;
import ro.unibuc.helpdesk.model.Comment;
import ro.unibuc.helpdesk.model.Customer;
import ro.unibuc.helpdesk.model.Priority;
import ro.unibuc.helpdesk.model.Ticket;
import ro.unibuc.helpdesk.model.TicketStatus;
import ro.unibuc.helpdesk.repository.AgentRepository;
import ro.unibuc.helpdesk.repository.CategoryRepository;
import ro.unibuc.helpdesk.repository.CommentRepository;
import ro.unibuc.helpdesk.repository.CustomerRepository;
import ro.unibuc.helpdesk.repository.TicketRepository;
import ro.unibuc.helpdesk.service.AgentService;
import ro.unibuc.helpdesk.service.AuditService;
import ro.unibuc.helpdesk.service.CategoryService;
import ro.unibuc.helpdesk.service.CommentService;
import ro.unibuc.helpdesk.service.CustomerService;
import ro.unibuc.helpdesk.service.TicketService;

public class Main {

    private final Scanner scanner = new Scanner(System.in);

    private final CustomerService customerService;

    private final AgentService agentService;

    private final CategoryService categoryService;

    private final TicketService ticketService;

    private final CommentService commentService;

    public Main() {
        AuditService auditService = AuditService.getInstance();

        CustomerRepository customerRepository = new CustomerRepository();
        AgentRepository agentRepository = new AgentRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        TicketRepository ticketRepository = new TicketRepository();
        CommentRepository commentRepository = new CommentRepository();

        this.customerService = new CustomerService(customerRepository, ticketRepository, auditService);
        this.agentService = new AgentService(agentRepository, auditService);
        this.categoryService = new CategoryService(categoryRepository, auditService);
        this.ticketService = new TicketService(ticketRepository, customerRepository, categoryRepository, agentRepository, auditService);
        this.commentService = new CommentService(commentRepository, ticketRepository, auditService);
    }

    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();

        Main app = new Main();
        app.run();
    }

    public void run() {
        while (true) {
            printMenu();

            try {
                int option = readInt("Choose option: ");

                switch (option) {
                    case 1 -> addCustomer();
                    case 2 -> listCustomers();
                    case 3 -> addAgent();
                    case 4 -> listAgents();
                    case 5 -> addCategory();
                    case 6 -> listCategories();
                    case 7 -> createTicket();
                    case 8 -> listTickets();
                    case 9 -> updateTicketStatus();
                    case 10 -> assignTicketToAgent();
                    case 11 -> addCommentToTicket();
                    case 12 -> listCommentsForTicket();
                    case 13 -> deleteCustomer();
                    case 14 -> deleteTicket();
                    case 15 -> deleteAgent();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                
                ===== HELPDESK MENU =====
                1. Add customer
                2. List customers
                3. Add agent
                4. List agents
                5. Add category
                6. List categories
                7. Create ticket
                8. List tickets
                9. Update ticket status
                10. Assign ticket to agent
                11. Add comment to ticket
                12. List comments for ticket
                13. Delete customer by id
                14. Delete ticket by id
                15. Delete agent by id
                0. Exit
                """);
    }

    private void addCustomer() {
        String name = readString("Customer name: ");
        String email = readString("Customer email: ");

        customerService.addCustomer(name, email);
        System.out.println("Customer added.");
    }

    private void listCustomers() {
        for (Customer customer : customerService.getAllCustomers()) {
            System.out.println(customer.printDetails());
        }
    }

    private void addAgent() {
        String name = readString("Agent name: ");
        String email = readString("Agent email: ");

        agentService.addAgent(name, email);
        System.out.println("Agent added.");
    }

    private void listAgents() {
        for (Agent agent : agentService.getAllAgents()) {
            System.out.println(agent.printDetails());
        }
    }

    private void addCategory() {
        String name = readString("Category name: ");

        categoryService.addCategory(name);
        System.out.println("Category added.");
    }

    private void listCategories() {
        for (Category category : categoryService.getAllCategories()) {
            System.out.println(category.printDetails());
        }
    }

    private void createTicket() {
        String title = readString("Ticket title: ");
        String description = readString("Ticket description: ");

        System.out.println("Priority options: LOW, MEDIUM, HIGH");
        Priority priority = Priority.valueOf(readString("Priority: ").toUpperCase());

        int customerId = readInt("Customer id: ");
        int categoryId = readInt("Category id: ");

        ticketService.createTicket(title, description, priority, customerId, categoryId);
        System.out.println("Ticket created.");
    }

    private void listTickets() {
        for (Ticket ticket : ticketService.getAllTickets()) {
            System.out.println(ticket.printDetails());
        }
    }

    private void updateTicketStatus() {
        int ticketId = readInt("Ticket id: ");

        System.out.println("Status options: OPEN, IN_PROGRESS, CLOSED");
        TicketStatus newStatus = TicketStatus.valueOf(readString("New status: ").toUpperCase());

        ticketService.updateStatus(ticketId, newStatus);
        System.out.println("Ticket status updated.");
    }

    private void assignTicketToAgent() {
        int ticketId = readInt("Ticket id: ");
        int agentId = readInt("Agent id: ");

        ticketService.assignAgent(ticketId, agentId);
        System.out.println("Agent assigned to ticket.");
    }

    private void addCommentToTicket() {
        int ticketId = readInt("Ticket id: ");
        String message = readString("Comment: ");

        commentService.addComment(ticketId, message);
        System.out.println("Comment added.");
    }

    private void listCommentsForTicket() {
        int ticketId = readInt("Ticket id: ");

        for (Comment comment : commentService.getCommentsForTicket(ticketId)) {
            System.out.println(comment.printDetails());
        }
    }

    private void deleteCustomer() {
        int id = readInt("Customer id to delete: ");

        customerService.deleteCustomer(id);
        System.out.println("Customer deleted.");
    }

    private void deleteTicket() {
        int id = readInt("Ticket id to delete: ");

        ticketService.deleteTicket(id);
        System.out.println("Ticket deleted.");
    }

    private void deleteAgent() {
        int id = readInt("Agent id to delete: ");

        agentService.deleteAgent(id);
        System.out.println("Agent deleted.");
    }

    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }
}
