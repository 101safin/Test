<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Donation and Expense Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Donations and Expenses Overview</h2>
        <canvas id="donationExpenseChart" width="400" height="200"></canvas>
    </div>

    <script>
        const ctx = document.getElementById('donationExpenseChart').getContext('2d');
        const donations = <%= request.getAttribute("donations") %>;
        const expenses = <%= request.getAttribute("expenses") %>;
        const dates = <%= request.getAttribute("dates") %>;

        const myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: dates,
                datasets: [{
                    label: 'Donations',
                    data: donations,
                    borderColor: 'rgba(75, 192, 192, 1)',
                    fill: false,
                    borderWidth: 2
                }, {
                    label: 'Expenses',
                    data: expenses,
                    borderColor: 'rgba(255, 99, 132, 1)',
                    fill: false,
                    borderWidth: 2
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>