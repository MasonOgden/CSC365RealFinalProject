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
    <title>Search for books</title>
</head>
<body>
<div style="width:30%;height:50%;margin:10% auto;padding: 10px;">
<form method="post">
    <p><label for="Book Title">Book Title</label><br/><input type="text" name="bookTitle" id="bookTitle" value="" size="30"></p>
    <p><label for="Author">Author</label><br/><input type="text" name="author" id="author" value="" size="30"></p>
    <p><label for="Category">Category</label><br/><input type="text" name="category" id="category" value="" size="30"></p>
    <input type="submit">
</form>
<p><a href="books">Back to Books table</a></p>
</div>
</body>
</html>