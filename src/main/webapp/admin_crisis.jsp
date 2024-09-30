<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Crisis Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">Pending Crises for Approval</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Location</th>
                    <th>Severity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% List<Crisis> crises = (List<Crisis>) request.getAttribute("crises"); %>
                <% for (Crisis crisis : crises) { %>
                    <tr>
                        <td><%= crisis.getTitle() %></td>
                        <td><%= crisis.getDescription() %></td>
                        <td><%= crisis.getLocation() %></td>
                        <td><%= crisis.getSeverity() %></td>
                        <td>
                            <form action="crisis" method="POST">
                                <input type="hidden" name="crisis_id" value="<%= crisis.getId() %>">
                                <button type="submit" class="btn btn-success">Approve</button>
                                <button type="submit" class="btn btn-danger" formaction="reject-crisis">Reject</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>