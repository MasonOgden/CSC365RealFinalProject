<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservations</title>
    <style>
        table {
            font-family: bookman, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
Message:
<p>${message}</p>
<table>
    <thead>Reservations</thead>
    <tr><th>Student ID</th><th>Book ID</th><th>Copy Number</th><th>Start Date</th>
    <th>End Date</th><th>Fulfilled</th><th>Expired</th></tr>
    <c:forEach items="${reservations}" var="reservation">
        <tr>
            <td>${reservation.studentId}</td>
            <td>${reservation.bookId}</td>
            <td>${reservation.copyNum}</td>
            <td>${reservation.startDate}</td>
            <td>${reservation.endDate}</td>
            <td>${reservation.fulfilled}</td>
            <td>${reservation.expired}</td>
        </tr>
    </c:forEach>
</table>
<p><a href="create_checkout">Add</a></p>
</body>
</html>