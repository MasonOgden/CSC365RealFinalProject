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
    <title>Customer Form</title>
</head>
<body>
  <form method="post" action="edit_customer">
      <input type="text" name="id" id="id" value="${customer.id}" readonly="readonly">
      <input type="text" name="ssn" id="ssn" value="${customer.ssn}">
      <input type="text" name="name" id="name" value="${customer.name}">
      <input type="text" name="address" id="address" value="${customer.address}">
      <input type="text" name="phone" id="phone" value="${customer.phone}">
      <input type="submit">
  </form>
</body>
</html>