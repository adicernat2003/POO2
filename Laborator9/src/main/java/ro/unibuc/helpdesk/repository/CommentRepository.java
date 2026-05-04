package ro.unibuc.helpdesk.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.unibuc.helpdesk.config.DatabaseConnection;
import ro.unibuc.helpdesk.model.Comment;

public class CommentRepository {

    public void create(Comment c) {
        String sql = "INSERT INTO comments(ticket_id, message) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, c.getTicketId());
            stmt.setString(2, c.getMessage());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findByTicketId(int ticketId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE ticket_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        rs.getInt("ticket_id"),
                        rs.getString("message")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }
}
