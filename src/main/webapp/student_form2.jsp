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
    <title>Transaction</title>
</head>
<body>
<div style="width:30%;height:50%;margin:10% auto;padding: 10px;">
<form method="post" action="create_student">
    <p><label for="studentType">type</label><br/><input type="text" name="studentType" id="studentType" value="" size="30"></p>
    <p><label for="id">ID</label><br/><input type="text" name="id" id="id" value="" size="30"></p>
    <p><label for="firstName">First Name</label><br/><input type="text" name="firstName" id="firstName" value="" size="30"></p>
    <p><label for="lastName">Last Name</label><br/><input type="text" name="lastName" id="lastName" value="" size="30"></p>
    <input type="submit">
</form>
</div>
</body>
</html>