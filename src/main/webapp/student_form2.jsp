<%--
  Created by IntelliJ IDEA.
  User: toshihirokuboi
  Date: 2019-11-21
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a Student</title>
    <link rel="stylesheet" href="style.css">
</head>
<body style = "left-margin:auto; right-margin:auto;"><br>
    <div class = "form">
    <h4 style="color:#8F2581; face=bookman; size=25;">Edit an entry in the table</h4>
    <form method="post" action="create_student">
        <p><label for="studentType">type</label><br/><input type="text" name="studentType" id="studentType" value="" size="30"></p>
        <p><label for="id">ID</label><br/><input type="text" name="id" id="id" value="" size="30"></p>
        <p><label for="firstName">First Name</label><br/><input type="text" name="firstName" id="firstName" value="" size="30"></p>
        <p><label for="lastName">Last Name</label><br/><input type="text" name="lastName" id="lastName" value="" size="30"></p>
        <input type="submit">
    </form>
    <p><a href="students">Back to Students table</a></p>
    </div>
</body>
</html>