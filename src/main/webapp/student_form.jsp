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
    <title>Edit Student Entry</title>
    <link rel="stylesheet" href="style.css">
</head>
<body style = "left-margin:auto; right-margin:auto;"><br>
  <div class="form">
  <h4 style="color:#8F2581; face=bookman; size=25;">Edit an entry in the table</h4>
  <form method="post" action="edit_student">
      ID: <input type="text" name="id" id="id" value="${student.id}" readonly="readonly"><br><br>
      First Name: <input type="text" name="firstName" id="firstName" value="${student.firstName}"><br><br>
      Last Name: <input type="text" name="lastName" id="lastName" value="${student.lastName}"><br><br>
      Ug or Gr: <input type="text" name="studentType" id="studentType" value="${student.studentType}"><br><br>
      <input type="submit">
  </form>
  <p><a href="students">Back to Students table</a></p>
</div>
</body>
</html>