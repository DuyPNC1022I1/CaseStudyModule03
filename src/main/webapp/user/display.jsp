<%--
  Created by IntelliJ IDEA.
  User: THIS PC
  Date: 2/9/2023
  Time: 12:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Display User</title>
</head>
<body>
<h1>List Product</h1>
<table border="2">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Price</td>
        <td>Quantity</td>
        <td>Description</td>
        <td>Image</td>
        <td>Brand</td>
        </tf>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach items="${requestScope['products']}" var="product">
        <tr>
            <td>${product.getId()}</td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getQuantity()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getImage()}</td>
            <td>${product.getBrand().getName()}</td>
            <td>
                <a href="/server?action=view&id=${product.getId()}">
                    <button>View</button>
                </a>
            </td>
            <td>
                <a href="/server?action=buy&id=${product.getId()}">
                    <button>Add to your Cart</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<%--Tim san pham theo ten--%>
<form action="/user?action=searchName" method="post">
    <label>Search by name</label>
    <input type="text" name="searchByName" placeholder="Enter name to search!">
    <input type="submit" value="Submit">
</form>

<%--Tim san pham theo brand--%>
<form action="/user?action=searchByBrand" method="post">
    <label>Search by brand</label>
    <select name="searchByBrand">
        <c:forEach items="${requestScope['brands']}" var="brand">
            <option>${brand.getName()}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Submit">
</form>

<%--Tim san pham theo khoang gia--%>
<form action="/user?action=searchPrice" method="post">
    <label>Search by price</label>
    <input name="lowerPrice" type="text" placeholder="Lower price">
    <input name="upperPrice" type="text" placeholder="Upper price">
    <input type="submit" value="Submit">
</form>
</body>
</html>