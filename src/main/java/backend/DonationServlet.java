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

@WebServlet("/donation")
public class DonationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String donorName = request.getParameter("donor_name");
        String donorEmail = request.getParameter("donor_email");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO donations (donor_name, donor_email, amount) VALUES (?, ?, ?)");
            stmt.setString(1, donorName);
            stmt.setString(2, donorEmail);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("donation.jsp");
    }
}

