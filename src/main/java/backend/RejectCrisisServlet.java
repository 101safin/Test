package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reject-crisis")
public class RejectCrisisServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int crisisId = Integer.parseInt(request.getParameter("crisis_id"));

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            // Reject crisis by changing the status
            PreparedStatement stmt = conn.prepareStatement("UPDATE crises SET status = 'rejected' WHERE id = ?");
            stmt.setInt(1, crisisId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/crisis");
    }
}
