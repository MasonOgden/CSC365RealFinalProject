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
    <title>Select Year fot Summary</title>
</head>
<body>
<p style ="padding-center:140px;"> Select a Year to Summarize </p>
<div style="width:30%;height:50%;margin:10% auto;padding: 10px;">
    <form  method="post" action="summary">
      Year: <input type="text" name="year" id="year" value="2020" size="30">
      <input type="submit" value="Submit">
    </form>
</div>
<p><a href="students">Back to Students table</a></p>
</body>
</html>