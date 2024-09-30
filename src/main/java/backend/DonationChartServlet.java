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

@WebServlet("/donation-chart")
public class DonationChartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Double> donations = new ArrayList<>();
        List<Double> expenses = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            // Fetch daily donations
            PreparedStatement donationStmt = conn.prepareStatement("SELECT date, SUM(amount) as total FROM donations GROUP BY date");
            ResultSet rsDonation = donationStmt.executeQuery();
            while (rsDonation.next()) {
                donations.add(rsDonation.getDouble("total"));
                dates.add(rsDonation.getString("date"));
            }

            // Fetch daily expenses (similar query as above but with your expenses table)
            PreparedStatement expenseStmt = conn.prepareStatement("SELECT date, SUM(amount) as total FROM expenses GROUP BY date");
            ResultSet rsExpense = expenseStmt.executeQuery();
            while (rsExpense.next()) {
                expenses.add(rsExpense.getDouble("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("donations", donations);
        request.setAttribute("expenses", expenses);
        request.setAttribute("dates", dates);

        RequestDispatcher dispatcher = request.getRequestDispatcher("donation_chart.jsp");
        dispatcher.forward(request, response);
    }
}

