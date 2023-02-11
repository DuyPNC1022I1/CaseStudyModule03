<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/10/2023
  Time: 8:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cập nhật sản phẩm</title>
  <link rel="stylesheet" href="./assets/css/base.css">
  <link rel="stylesheet" href="./assets/css/edit.css">
  <link rel="stylesheet" href="./assets/font-icon/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;700&family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
</head>
<body>
<div id="main">
  <div class="modal">
    <a href="/admin" class="modal-background"></a>
    <div class="container">
      <h1 class="container-header">Chỉnh sửa sản phẩm</h1>
      <form class="container-form" action="/admin?action=update&id=${product.getId()}" method="post">
        <div class="container-form__item">
          <label class="container-form__item-label" for="name">Tên sản phẩm: </label>
          <input class="container-form__item-input" type="text" name="name" id="name" value="${product.getName()}">
        </div>

        <div class="container-form__item">
          <label class="container-form__item-label" for="price">Giá sản phẩm: </label>
          <input class="container-form__item-input" type="text" name="price" id="price" value="${product.getPrice()}">
        </div>

        <div class="container-form__item">
          <label class="container-form__item-label" for="quantity">Số lượng: </label>
          <input class="container-form__item-input" type="text" name="quantity" id="quantity" value="${product.getQuantity()}">
        </div>

        <div class="container-form__item">
          <label class="container-form__item-label" for="desc">Mô tả: </label>
          <input class="container-form__item-input" type="text" name="desc" id="desc" value="${product.getDescription()}">
        </div>

        <div class="container-form__item">
          <label class="container-form__item-label" for="img">Ảnh sản phẩm: </label>
          <input class="container-form__item-input" type="text" name="img" id="img" value="${product.getImage()}">
        </div>

        <div class="container-form__item container-form__select">
          <div>
            <p class="container-form__item-label container-form__select-header">Thương hiệu: </p>
          </div>
          <select name="brand" id="brand">
            <option value="${product.getBrand().getId()}">${product.getBrand().getName()}</option>
          </select>
        </div>

        <div class="container-form__btn">
          <button class="btn" type="submit">Sửa</button>
        </div>

        <a href="/admin" class="container-form__close">
          <i class="fa-solid fa-xmark"></i>
        </a>
      </form>
    </div>
  </div>
</div>
</body>
</html>
