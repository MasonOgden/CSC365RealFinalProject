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
    <tr><th>id</th><th>First Name</th><th>Last Name</th><th>Type</th><th></th></tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.studentType}</td>
            <td><a data-id="${student.id}" href="edit_student?id=${student.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="create_student">Add</a></p>
</body>
</html>
