<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Volunteer Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">Pending Volunteer Approvals</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% List<User> volunteers = (List<User>) request.getAttribute("volunteers"); %>
                <% for (User volunteer : volunteers) { %>
                    <tr>
                        <td><%= volunteer.getName() %></td>
                        <td><%= volunteer.getEmail() %></td>
                        <td><%= volunteer.getPhone() %></td>
                        <td>
                            <form action="volunteer" method="POST">
                                <input type="hidden" name="volunteer_id" value="<%= volunteer.getId() %>">
                                <button type="submit" class="btn btn-success">Approve</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>