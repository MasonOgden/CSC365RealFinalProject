<%--
  Created by IntelliJ IDEA.
  User: toshihirokuboi
  Date: 2019-05-29
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Form</title>
    <link rel="stylesheet" href="style.css">
</head>
<body style = "left-margin:auto; right-margin:auto;"><br>
  <div class="form">
  <h4 style="color:#8F2581; face=bookman; size=25;">Edit an entry in the table</h4>
  <form method="post" action="new_checkout">
      Student ID: <input type="text" name="StudentId" id="StudentId" value="${student.id}" <br><br>
      Last Name: <input type="text" name="lastName" id="lastName" value="${student.lastName}"><br><br>
      Book Title: <input type="text" name="BookTitle" id="lastName" value="${Book.title}"><br><br>
      Ug or Gr: <input type="text" name="studentType" id="studentType" value="${student.studentType}"><br><br>
      <input type="submit">
  </form>
  <p><a href="">Back to Home</a></p>
</div>
</body>
</html>