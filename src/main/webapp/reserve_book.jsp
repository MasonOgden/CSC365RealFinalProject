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
    <title>Reserve a Book</title>
    <link rel="stylesheet" href="style.css">
</head>
<body style = "left-margin:auto; right-margin:auto;"><br>
  <div class="form"

  <h4 style="color:#8F2581; face=bookman; size=25;">Reserve a Book</h4>
  <form method="post" action="reserve_book">
      Student ID: <input type="text" name="studentId" id="studentId"<br><br>
      Book ID: <input type="text" name="bookId" id="bookId" value="${book.id}" readonly="readonly"><br><br>
      Copy ID: <input type="text" name="copyId" id="copyId" value="${book.copyNum}" readonly="readonly"><br><br>
      Title: <input type="text" name="studentId" id="studentId" value="${book.title}" readonly = "readonly"><br><br>
      <input type="submit">
  </form>
  <p><a href="students">Back to Students table</a></p>
</div>
</body>
</html>