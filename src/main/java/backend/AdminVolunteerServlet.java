package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/volunteer")
public class AdminVolunteerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> pendingVolunteers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            // Fetch volunteers pending approval
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE role = 'volunteer' AND approved = 0");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User volunteer = new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("phone"),rs.getString("role"));
                pendingVolunteers.add(volunteer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("volunteers", pendingVolunteers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_volunteer.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int volunteerId = Integer.parseInt(request.getParameter("volunteer_id"));

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            // Approve volunteer
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET approved = 1 WHERE id = ?");
            stmt.setInt(1, volunteerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/volunteer");
    }
}

