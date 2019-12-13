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
    <h5 style= "text-align:center;">Students</h5>
    <tr><th>Book ID</th><th>Jan</th><th>Feb</th><th>Mar</th><th></th></tr>
    <c:forEach items="${summaries}" var="summary">
        <tr>
            <td>${summary.bookId}</td>
            <td>${summary.January}</td>
            <td>${summary.Feburary}</td>
            <td>${summary.March}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
