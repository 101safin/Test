<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Reports</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">Generate Reports</h2>

        <div class="row">
            <div class="col-md-4">
                <h4>Donation Reports</h4>
                <a href="/admin/donation-report?type=csv" class="btn btn-primary">Download CSV</a>
                <a href="/admin/donation-report?type=excel" class="btn btn-success">Download Excel</a>
            </div>

            <div class="col-md-4">
                <h4>Expense Reports</h4>
                <a href="/admin/expense-report?type=csv" class="btn btn-primary">Download CSV</a>
                <a href="/admin/expense-report?type=excel" class="btn btn-success">Download Excel</a>
            </div>

            <div class="col-md-4">
                <h4>Inventory Reports</h4>
                <a href="/admin/inventory-report?type=csv" class="btn btn-primary">Download CSV</a>
                <a href="/admin/inventory-report?type=excel" class="btn btn-success">Download Excel</a>
            </div>
        </div>
    </div>
</body>
</html>