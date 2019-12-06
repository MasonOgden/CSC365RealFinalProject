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
<form method="post" action="transaction">
    <p><label for="id">Customer Id</label><br/><input type="text" name="cid" id="id" value="" size="30"></p>
    <p><label for="card_number">Card Number</label><br/><input type="text" name="card_number" id="card_number" value="" size="30"></p>
    <p><label for="vid">Vendor Id</label><br/><input type="text" name="vid" id="vid" value="" size="30"></p>
    <p><label for="date">Date</label><br/><input type="text" name="date" id="date" value="" size="30"></p>
    <p><label for="amount">Amount</label><br/><input type="text" name="amount" id="amount" value="" size="30"></p>
    <input type="submit">
</form>
</div>
</body>
</html>