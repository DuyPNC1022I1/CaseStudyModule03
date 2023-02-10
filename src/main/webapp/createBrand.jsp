<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/10/2023
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tạo sản phẩm</title>
  <link rel="stylesheet" href="./assets/css/base.css">
  <link rel="stylesheet" href="./assets/css/create.css">
  <link rel="stylesheet" href="./assets/font-icon/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;700&family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
</head>
<body>
<div id="main">
  <div class="modal">
    <a href="/admin" class="modal-background"></a>
    <div class="container">
      <h1 class="container-header">Thêm thương hiệu</h1>
      <form class="container-form" action="/admin?action=createBrand" method="post">
        <div class="container-form__item">
          <label class="container-form__item-label" for="name">Tên thương hiệu: </label>
          <input class="container-form__item-input" type="text" name="name" id="name" placeholder="Nhập tên thương hiệu...">
        </div>

        <div class="container-form__btn">
          <button class="btn" type="submit">Tạo</button>
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
</html>
