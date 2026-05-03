package ro.unibuc.helpdesk.repository;

import ro.unibuc.helpdesk.config.DatabaseConnection;
import ro.unibuc.helpdesk.model.Priority;
import ro.unibuc.helpdesk.model.Ticket;
import ro.unibuc.helpdesk.model.TicketStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    public void create(Ticket t) {
        String sql = """
                INSERT INTO tickets(title, description, status, priority, customer_id, agent_id, category_id)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, t.getTitle());
            stmt.setString(2, t.getDescription());
            stmt.setString(3, t.getStatus().name());
            stmt.setString(4, t.getPriority().name());
            stmt.setInt(5, t.getCustomerId());

            if (t.getAgentId() == null) {
                stmt.setNull(6, Types.INTEGER);
            } else {
                stmt.setInt(6, t.getAgentId());
            }

            stmt.setInt(7, t.getCategoryId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket findById(int id) {
        String sql = "SELECT * FROM tickets WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Ticket(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        TicketStatus.valueOf(rs.getString("status")),
                        Priority.valueOf(rs.getString("priority")),
                        rs.getInt("customer_id"),
                        (Integer) rs.getObject("agent_id"),
                        rs.getInt("category_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        TicketStatus.valueOf(rs.getString("status")),
                        Priority.valueOf(rs.getString("priority")),
                        rs.getInt("customer_id"),
                        (Integer) rs.getObject("agent_id"),
                        rs.getInt("category_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tickets;
    }

    public void updateStatus(int ticketId, TicketStatus status) {
        String sql = "UPDATE tickets SET status=? WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            stmt.setInt(2, ticketId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void assignAgent(int ticketId, int agentId) {
        String sql = "UPDATE tickets SET agent_id=? WHERE id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, agentId);
            stmt.setInt(2, ticketId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}