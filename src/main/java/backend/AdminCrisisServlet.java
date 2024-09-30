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

@WebServlet("/admin/crisis")
public class AdminCrisisServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Crisis> pendingCrises = new ArrayList<>();

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            // Fetch crises pending approval
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM crises WHERE status = 'pending'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Crisis crisis = new Crisis(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("severity"),
                    rs.getString("status")
                );
                pendingCrises.add(crisis);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("crises", pendingCrises);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_crisis.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int crisisId = Integer.parseInt(request.getParameter("crisis_id"));

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            // Approve crisis
            PreparedStatement stmt = conn.prepareStatement("UPDATE crises SET status = 'approved' WHERE id = ?");
            stmt.setInt(1, crisisId);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/crisis");
    }
}

