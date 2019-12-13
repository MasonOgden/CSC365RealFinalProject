
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Students</title>
    <style>
        table {
            font-family: bookman, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 20px;
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
    <h5 style= "text-align:center;">Summary for ${year}</h5>
    <tr><th>Book ID</th><th>Jan</th><th>Feb</th><th>Mar</th>
		<th>Apr</th><th>May</th><th>Jun</th>
		<th>Jul</th><th>Aug</th><th>Sep</th>
		<th>Oct</th><th>Nov</th><th>Dec</th><th>Totals</th>
		</tr>
    <c:forEach items="${summaries}" var="summary">
        <tr>
            <td>${summary.bookId}</td>
            <td>${summary.january}</td>
            <td>${summary.february}</td>
            <td>${summary.march}</td>
            <td>${summary.april}</td>
            <td>${summary.may}</td>
            <td>${summary.june}</td>
            <td>${summary.july}</td>
            <td>${summary.august}</td>
            <td>${summary.september}</td>
            <td>${summary.october}</td>
            <td>${summary.november}</td>
            <td>${summary.december}</td>
            <td>${summary.bookTotal}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>