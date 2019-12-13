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
    <h4 style="color:#8F2581; face=bookman; size=25;">Search for a book</h4>
    <form method="post" action="search_book">
        <p><label for="title">Title</label><br/><input type="text" name="title" id="title" value="" size="30"></p>
        <p><label for="author">Author</label><br/><input type="text" name="author" id="author" value="" size="30"></p>
        <p><label for="category">Category</label><br/><input type="text" name="category" id="category" value="" size="30"></p>
        <p><label for="id">ISBN</label><br/><input type="text" name="id" id="id" value="" size="30"></p>
        <input type="submit">
    </form>
    <p><a href=" ">Home</a></p>
    </div>
</body>
</html>