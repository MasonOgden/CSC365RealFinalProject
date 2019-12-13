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
    <tr><th>ID</th><th>Copy ID</th><th>Title</th><th>Author</th><th>Category</th><th>Checkout</th><th></th></tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.copyNum}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.category}</td>
            <td><a data-id="${book.id}{book.copyNum}" href="checkout_book?id=${book.id}&copyNum=${book.copyNum}">Checkout</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
