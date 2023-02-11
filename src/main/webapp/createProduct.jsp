<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/10/2023
  Time: 8:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm sản phẩm</title>
    <link rel="stylesheet" href="./assets/css/base.css">
    <link rel="stylesheet" href="./assets/css/create.css">
    <link rel="stylesheet" href="./assets/font-icon/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;700&family=Roboto:wght@100;300;400;500;700&display=swap"
          rel="stylesheet">
</head>
<body>
<div id="main">
    <div class="modal">
        <a href="/admin" class="modal-background"></a>
        <div class="container">
            <h1 class="container-header">Thêm Sản phẩm</h1>
            <form class="container-form" action="/admin?action=create" method="post">
                <div class="container-form__item">
                    <label class="container-form__item-label" for="name">Tên sản phẩm: </label>
                    <input class="container-form__item-input" type="text" name="name" id="name"
                           placeholder="Nhập tên sản phẩm...">
                </div>

                <div class="container-form__item">
                    <label class="container-form__item-label" for="price">Giá sản phẩm: </label>
                    <input class="container-form__item-input" type="text" name="price" id="price"
                           placeholder="Nhập giá sản phẩm...">
                </div>

                <div class="container-form__item">
                    <label class="container-form__item-label" for="quantity">Số lượng: </label>
                    <input class="container-form__item-input" type="text" name="quantity" id="quantity"
                           placeholder="Nhập số lượng sản phẩm...">
                </div>

                <div class="container-form__item">
                    <label class="container-form__item-label" for="desc">Mô tả: </label>
                    <input class="container-form__item-input" type="text" name="description" id="desc"
                           placeholder="Nhập mô tả sản phẩm...">
                </div>

                <div class="container-form__item">
                    <label class="container-form__item-label" for="img">Ảnh sản phẩm: </label>
                    <input class="container-form__item-input" type="text" name="image" id="img"
                           placeholder="Link ảnh sản phẩm...">
                </div>

                <div class="container-form__item container-form__select">
                    <div>
                        <p class="container-form__item-label container-form__select-header">Thương hiệu: </p>
                    </div>
                    <select name="brand" id="brand">
                        <c:forEach items="${requestScope['brands']}" var="brand">
                            <option>${brand.getName()}</option>
                        </c:forEach>
                        <c:forEach items="${brands}" var="brand">
                            <option value="${brand.getName()}">${brand.getName()}</option>
                        </c:forEach>
                    </select>
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