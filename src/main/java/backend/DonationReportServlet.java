package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/donation-report")
public class DonationReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportType = request.getParameter("type");

        if ("csv".equalsIgnoreCase(reportType)) {
            generateCSVReport(response);
        } else if ("excel".equalsIgnoreCase(reportType)) {
            generateExcelReport(response);
        }
    }

    private void generateCSVReport(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=donation_report.csv");

        try (PrintWriter writer = response.getWriter(); Connection conn = DatabaseConnection.initializeDatabase()) {
            writer.println("Date,Total Donations");

            PreparedStatement stmt = conn.prepareStatement("SELECT date, SUM(amount) as total FROM donations GROUP BY date");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                writer.println(rs.getString("date") + "," + rs.getDouble("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateExcelReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=donation_report.xls");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Donation Report");

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT date, SUM(amount) as total FROM donations GROUP BY date");
            ResultSet rs = stmt.executeQuery();

            HSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Date");
            headerRow.createCell(1).setCellValue("Total Donations");

            int rowNum = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getString("date"));
                row.createCell(1).setCellValue(rs.getDouble("total"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}

