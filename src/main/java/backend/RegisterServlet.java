package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password (update based on your configuration)
    private final String jdbcURL = "jdbc:mysql://localhost:3306/disaster_management";
    private final String jdbcUsername = "root";  // Replace with your DB username
    private final String jdbcPassword = "password";  // Replace with your DB password

    // SQL query to insert volunteer into the table
    private final String INSERT_VOLUNTEER_SQL = "INSERT INTO volunteers"
        + " (name, username, password, email, phone, age) VALUES (?, ?, ?, ?, ?, ?);";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Get form data from the request
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String ageStr = request.getParameter("age");
        int age = Integer.parseInt(ageStr);  // Convert string to int for age
        String password = request.getParameter("password");  // In production, hash the password

        try (PrintWriter out = response.getWriter()) {
            // Register the volunteer in the database
            boolean isRegistered = registerVolunteer(name, username, password, email, phone, age);
            
            if (isRegistered) {
                // Registration successful
                out.println("<div class='alert alert-success' role='alert'>");
                out.println("Registration successful! You can now <a href='login.jsp'>login</a>.");
                out.println("</div>");
            } else {
                // Registration failed
                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("Registration failed! Please try again.");
                out.println("</div>");
            }
        }
    }

    // Method to register the volunteer in the database
    private boolean registerVolunteer(String name, String username, String password, String email, String phone, int age) {
        boolean rowInserted = false;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get the connection
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VOLUNTEER_SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);  // In production, store the hashed password
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);
            preparedStatement.setInt(6, age);

            // Execute the query
            rowInserted = preparedStatement.executeUpdate() > 0;

            // Close the connection
            preparedStatement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rowInserted;
    }
}