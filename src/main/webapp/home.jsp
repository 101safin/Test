<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Disaster Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Include Chart.js for chart rendering (optional) -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="container mt-5">
        <!-- Welcome Message -->
        <h1 class="text-center mb-4">Disaster Management Dashboard</h1>

        <!-- Row for Fund, Crisis, Volunteer Sections -->
        <div class="row">
            <!-- Fund Section -->
            <div class="col-md-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <h5 class="card-title text-center">Total Donated Funds</h5>
                        <h3 class="text-center text-success" id="totalFunds">$0.00</h3>

                        <!-- Chart Placeholder for Donations and Expenses -->
                        <canvas id="fundChart" width="100" height="100"></canvas>
                        
                        <!-- Button to Donation Page -->
                        <div class="d-grid mt-3">
                            <a href="donation.jsp" class="btn btn-primary">Go to Donation Page</a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Crisis Section -->
            <div class="col-md-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <h5 class="card-title text-center">Recent Crises</h5>
                        <!-- List of Recent Crises -->
                        <ul class="list-group list-group-flush" id="crisisList">
                            <li class="list-group-item">Flood in Area X</li>
                            <li class="list-group-item">Earthquake in Region Y</li>
                            <li class="list-group-item">Tsunami in Coastal Z</li>
                        </ul>

                        <!-- Button to Crisis Page -->
                        <div class="d-grid mt-3">
                            <a href="crisis.jsp" class="btn btn-warning">View Crises</a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Volunteer Section -->
            <div class="col-md-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <h5 class="card-title text-center">Available Volunteers</h5>
                        <!-- List of Available Volunteers -->
                        <ul class="list-group list-group-flush" id="volunteerList">
                            <li class="list-group-item">John Doe - Age: 30</li>
                            <li class="list-group-item">Jane Smith - Age: 25</li>
                            <li class="list-group-item">Alice Johnson - Age: 22</li>
                        </ul>

                        <!-- Button to Volunteer Page -->
                        <div class="d-grid mt-3">
                            <a href="volunteer.jsp" class="btn btn-info">View Volunteers</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Chart.js Script to Display Bar Chart for Donations and Expenses -->
    <script>
        const ctx = document.getElementById('fundChart').getContext('2d');
        const fundChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5'],
                datasets: [{
                    label: 'Donations',
                    data: [500, 1000, 1500, 700, 900],
                    backgroundColor: 'rgba(54, 162, 235, 0.6)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }, {
                    label: 'Expenses',
                    data: [200, 800, 900, 600, 300],
                    backgroundColor: 'rgba(255, 99, 132, 0.6)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

        // Mock AJAX call to fetch total donated funds (for demo)
        document.getElementById('totalFunds').innerText = "$12,300.00";
    </script>
</body>
</html>