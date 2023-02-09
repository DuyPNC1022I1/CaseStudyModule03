<%--
  Created by IntelliJ IDEA.
  User: THIS PC
  Date: 2/9/2023
  Time: 12:30 AM
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
        <td>Id </td>
        <td>Name</td>
        <td>Price</td>
        <td>Quantity</td>
        <td>Description</td>
        <td>Image</td>
        <td>Brand</td></tf>
        <td colspan="3">Action</td>
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
                <a href="admin/create.jsp"><button>Create new</button></a>
            </td>
            <td>
                <a href="/admin?action=update&id=${product.getId()}"><button>Edit</button></a>
            </td>
            <td>
                <a href="/admin?action=delete&id=${product.getId()}"><button>Delete</button></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
