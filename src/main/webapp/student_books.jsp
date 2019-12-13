<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
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
    <thead>Books</thead>
    <tr><th>Student ID</th><th>Book ID</th><th>Copy Number</th><th>Start Date</th>
    <th>Return Date</th><th>Due Date</th><th>Due Date Extended</th>><th>Return</th></tr>
    <c:forEach items="${checkouts}" var="checkout">
        <tr>
            <td>${checkout.studentId}</td>
            <td>${checkout.bookId}</td>
            <td>${checkout.copyNum}</td>
            <td>${checkout.startDate}</td>
            <td>${checkout.returnDate}</td>
            <td>${checkout.dueBack}</td>
            <td>${checkout.ddExtended}</td>
            <td><a data-id="${book.id}{book.copyNum}" href="return_book?id=${book.id}&copyNum=${book.copyNum}">Return</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
