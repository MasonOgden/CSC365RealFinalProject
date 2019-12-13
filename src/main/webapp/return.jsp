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
    <title>Return a book</title>
    <link rel="stylesheet" href="style.css">
</head>
<body style = "left-margin:auto; right-margin:auto;"><br>
    <div class = "form">
    <h4 style="color:#8F2581; face=bookman; size=25;">Return a book</h4>
    <form method="post" action="return">
        <p><label for="studentId">Student ID</label><br/><input type="text" name="studentId" id="studentId" value="" size="30"></p>
        <p><label for="bookId">Book ID</label><br/><input type="text" name="bookId" id="bookId" value="" size="30"></p>
        <p><label for="returnDate">Return Date</label><br/><input type="text" name="returnDate" id="returnDate" value="" size="30"></p>
        <input type="submit">
    </form>
    <p><a href=" ">Home</a></p>
    </div>
</body>
</html>