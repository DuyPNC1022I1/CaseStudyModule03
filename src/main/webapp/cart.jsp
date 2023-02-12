<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/12/2023
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cart</title>
  <link rel="stylesheet" href="./assets/css/base.css">
  <link rel="stylesheet" href="./assets/css/grid.css">
  <link rel="stylesheet" href="./assets/css/cart.css">
  <link rel="stylesheet" href="./assets/font-icon/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;700&family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
</head>
<body>
<div id="main">
  <div class="grid wide">
    <div class="container">
      <div class="container__product">
        <c:forEach items="products" var="product">
          <div class="row">
            <div class="col l-2">
              <img src="./assets/img/category/atlantic/1462484998_dong-ho-thuy-sy-phien-ban-gioi-han9.jpg" alt="">
            </div>
            <div class="col l-8 container-infomation">
              <div class="product-infomation">
                <div class="product-name">${product.getName()}</div>
                <div class="product-desc">${product.getDescription()}</div>
                <div class="product-brand">
                  Thương hiệu:
                  <div class="product-brand__name">${product.getBrand().getName()}</div>
                </div>
                <input type="number" value="3">
              </div>
            </div>
            <div class="col l-2 container-product__price">
              <div class="conntainer-price">
                <div class="product-price">Giá: ${product.getPrice()}</div>
                <a class="product-delete__cart" href="#">Xoá</a>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
      <div class="container__user">
        <h2 class="conntainer__user-header">
          Thông tin khách hàng
        </h2>
        <p class="container__user-note">Lưu ý: Các ô cần điền đầy đủ thông tin</p>
        <form class="container__user-form">
          <div class="row container__user-form-item">
            <label class="col l-2 container__user-label" for="name">Họ và tên: </label>
            <input class="col l-10 container__user-input" type="text" placeholder="Nhập họ tên" name="name" id="name">
          </div>

          <div class="row container__user-form-item">
            <label class="col l-2 container__user-label" for="phone">Số điện thoại: </label>
            <input class="col l-10 container__user-input" type="text" name="phone" id="phone" placeholder="Nhập số điện thoại">
          </div>

          <div class="row container__user-form-item">
            <label class="col l-2 container__user-label" for="address">Địa chỉ: </label>
            <input class="col l-10 container__user-input" type="text" name="address" id="address" placeholder="Nhập địa chỉ">
          </div>
        </form>
      </div>
      <div class="container__footer">
        <div class="row container__price-cart">
          <p class="col l-3">Tổng tiền thanh toán</p>
          <p class="col l-9 price-cart">23.590.380 VND</p>
        </div>
        <div class="row container__footer-btn">
          <button class="btn">Đặt Hàng</button>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
