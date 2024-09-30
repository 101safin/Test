<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Donate to Help</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h3 class="text-center mb-4">Donate to Support Disaster Relief</h3>

                <!-- Card Layout for Better UI -->
                <div class="card shadow-sm">
                    <div class="card-body">
                        <!-- Donation Form -->
                        <form action="donation" method="POST">
                            <!-- Donor Name -->
                            <div class="mb-3">
                                <label for="donor_name" class="form-label">Donor Name</label>
                                <input type="text" class="form-control" id="donor_name" name="donor_name" required placeholder="Enter your full name">
                            </div>

                            <!-- Donor Email -->
                            <div class="mb-3">
                                <label for="donor_email" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="donor_email" name="donor_email" required placeholder="Enter your email">
                            </div>

                            <!-- Donation Amount -->
                            <div class="mb-3">
                                <label for="amount" class="form-label">Donation Amount</label>
                                <input type="number" class="form-control" id="amount" name="amount" required placeholder="Enter the amount (USD)">
                            </div>

                            <!-- Submit Button -->
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Donate Now</button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Thank You Message or Info Section -->
                <div class="text-center mt-4">
                    <p>Thank you for your generosity! Your contribution will make a difference in supporting those affected by disasters.</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
