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
</head>
<body>
  <form method="post" action="edit_student">
      <input type="text" name="id" id="id" value="${student.id}" readonly="readonly">
      <input type="text" name="firstName" id="firstName" value="${student.firstName}">
      <input type="text" name="lastName" id="lastName" value="${student.lastName}">
      <input type="text" name="studentType" id="studentType" value="${student.studentType}">
      <input type="submit">
  </form>
</body>
</html>