<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
    <style>
        table {
            font-family: fantasy, sans-serif;
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
    <tr><th>id</th><th>name</th><th></th></tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td><a data-id="${book.id}" href="edit_book?id=${book.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="create_book">Add</a></p>
</body>
</html>
