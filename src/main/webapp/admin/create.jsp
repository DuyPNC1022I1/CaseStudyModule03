<%--
  Created by IntelliJ IDEA.
  User: THIS PC
  Date: 2/9/2023
  Time: 3:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create new product</title>
</head>
<body>
<h1>Create new product</h1>
<form action="/admin?action=create" method="post">
  <label>Name: </label>
  <input type="text" name="name" placeholder="name"><br>
  <label>Price: </label>
  <input type="text" name="price" placeholder="0"><br>
  <label>Quantity: </label>
  <input type="text" name="quantity" placeholder="0"><br>
  <label>Description</label>
  <input type="text" name="description" placeholder="description"><br>
  <label>Image</label>
  <input type="text" name="image" placeholder="link image"><br>
  <label>Brand</label>
  <select name="brand">
    <c:forEach items="${requestScope['brands']}" var="brand">
      <option>${brand.getName()}</option>
    </c:forEach>
  </select>
  <input type="submit" value="Submit">
</form>
</body>
</html>
