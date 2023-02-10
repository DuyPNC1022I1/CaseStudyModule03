<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/10/2023
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NHÓM 1</title>
    <link rel="stylesheet" href="./assets/css/base.css">
    <link rel="stylesheet" href="./assets/css/grid.css">
    <link rel="stylesheet" href="./assets/css/main.css">
    <link rel="stylesheet" href="./assets/font-icon/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;700&family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->
</head>
<body>
<main>
    <div id="header">
        <div class="grid wide">
            <div class="row">
                <div class="col l-2 header-logo">
                    <img class="header-logo__img" src="./assets/img/logo/logo1.png" alt="">
                </div>
                <div class="col l-5 header-search">
                    <input class="input-search" type="search" placeholder="Nhập từ khoá tìm kiếm...">
                    <button class="btn-search">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </div>
                <div class="col l-5">
                    <div class="row header-top__right">
                        <div class="col l-4 header-top__right-item">
                            <div class="header-top__right-icon">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </div>
                            <div class="header__text">
                                <p class="header-top__right-text-top">Giỏ hàng</p>
                                <p class="header-top__right-text-bot">[0] sản phẩm</p>
                            </div>
                        </div>
                        <div class="col l-4 header-top__right-item">
                            <div class="header-top__right-icon">
                                <i class="fa-solid fa-phone"></i>
                            </div>
                            <div class="header__text">
                                <p class="header-top__right-text-top">Hotline</p>
                                <p class="header-top__right-text-bot">1800.6005</p>
                            </div>
                        </div>
                        <div class="col l-4 header-top__right-item">
                            <div class="header__container">
                                <a href="" class="header-top__right-login">Đăng Nhập</a>
                                <a href="" class="header-top__right-register">Đăng Kí</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col l-2 header-bottom__item header-botom__category">
                    <i class="header-bottom__item-icon fa-solid fa-caret-down"></i>
                    <p>Tất cả thương hiệu</p>
                    <ul class="header-botom__item-menu">

                        <!-- Phần lấy hãng đồng hồ -->
                        <li class="menu-item">
                            <a class="menu-item__link" href="">Đồng hồ Philippe Auguste</a>
                        </li>

                    </ul>
                </div>
                <div class="col l-8 header-bottom__item">
                    <div class="header__navbar">
                        <div class="row">
                            <div class="col l-3 header__navbar-item">
                                <a href="" class="header__navbar-link">Giới thiệu</a>
                            </div>
                            <div class="col l-3 header__navbar-item">
                                <a href="" class="header__navbar-link">Chính sách bảo mật</a>
                            </div>
                            <div class="col l-3 header__navbar-item">
                                <a href="" class="header__navbar-link">Chính sách bảo hành</a>
                            </div>
                            <div class="col l-3 header__navbar-item">
                                <a href="" class="header__navbar-link">Tin tức sự kiện</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col l-3 header-bottom__item">

                </div>
            </div>
        </div>
    </div>
    <div id="container">
        <div class="grid wide">
            <div class="row container-slide">
                <img src="./assets/img/slide/slide.jpg" alt="">
            </div>
        </div>
    </div>

    <div id="body">
        <div class="grid wide">
            <div class="row">
                <div class="col l-2 filter">
                    <div class="filter__header">
                        <i class="filter__header-icon fa-solid fa-filter"></i>
                        <h3>Bộ lọc tìm kiếm</h3>
                    </div>
                    <div class="filter__category">
                        <p class="filter__category-head">
                            Theo Hãng:
                        </p>
                        <ul class="filter__category-list">
                            <li class="filter__category-item">
                                <input class="filter__category-item-check" type="checkbox" name="" value="">
                                <p>Philippe Auguste</p>
                            </li>
                            <li class="filter__category-item">
                                <input class="filter__category-item-check" type="checkbox" name="" value="">
                                <p>Atlantic Swiss</p>
                            </li>
                            <li class="filter__category-item">
                                <input class="filter__category-item-check" type="checkbox" name="" value="">
                                <p>Diamond D</p>
                            </li>
                            <li class="filter__category-item">
                                <input class="filter__category-item-check" type="checkbox" name="" value="">
                                <p>Epos Swiss</p>
                            </li>
                        </ul>
                    </div>
                    <div class="filter__category">
                        <p class="filter__category-head">
                            Theo Khoảng Giá:
                        </p>
                        <div class="filter__category-list">
                            <div class="row">
                                <div class="col l-12 filter__category-list-input">
                                    <p class="filter__category-list-text">Từ: </p>
                                    <input class="filter-input" type="text" value="" placeholder="Từ">
                                </div>
                                <div class="col l-12 filter__category-list-input">
                                    <p class="filter__category-list-text">Đến:</p>
                                    <input class="filter-input" type="text" value="" placeholder="Đến">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col l-10 content">
                    <div class="row content__container-nav">
                        <div class="col l-3">
                            <div class="content__container-nav-item">
                                <a class="content__container-nav-link" href="">
                                    <img src="./assets/img/category/atlantic/at_logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col l-3">
                            <div class="content__container-nav-item">
                                <a class="content__container-nav-link" href="">
                                    <img src="./assets/img/category/diamond/dm_logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col l-3">
                            <div class="content__container-nav-item">
                                <a class="content__container-nav-link" href="">
                                    <img src="./assets/img/category/epos/ep_logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col l-3">
                            <div class="content__container-nav-item">
                                <a class="content__container-nav-link" href="">
                                    <img src="./assets/img/category/philippe-auguste/pa_logo.png" alt="">
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="row content__container-arrange">
                        <div class="content__nav-arrange">
                            <div class="content__nav-arrange-head">
                                <p>Sắp xếp: </p>
                                <i class="content__nav-arrange-head-icon fa-solid fa-chevron-down"></i>
                            </div>

                            <ul class="content__nav-arrange-menu">
                                <li class="content__nav-arrange-item">Giá từ thấp đến cao</li>
                                <li class="content__nav-arrange-item">Giá từ cao đến thấp</li>
                            </ul>
                        </div>
                    </div>

                    <div class="row">

                        <!-- DANH SÁCH SẢN PHẨM -->
                        <!-- phần truyền database Product -->
                        <div class="col l-3">
                            <div class="product-container">
                                <div class="product-container__img">
                                    <img class="product-img" src="./assets/img/category/atlantic/1462484998_dong-ho-thuy-sy-phien-ban-gioi-han9.jpg" alt="">
                                </div>
                                <div class="product-content">
                                    <h3 class="product-content__name">Đồng hồ Atlantic Swiss AT-52851.44.25</h3>
                                    <p class="product-content__desc">Size mặt 40mm - Kính cứng</p>
                                    <div class="product-content__container-price">
                                        Giá:
                                        <p class="product-content__price">130.000.000đ</p>
                                    </div>
                                    <div class="product-content__status">Sẵn sàng</div>
                                    <div class="product-btn__action">
                                        <a class="product-btn__action-btn" href="">Sửa</a>

                                        <a class="product-btn__action-btn" href="">Xoá</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="footer">
        <div class="footer-content">
            <div class="grid wide">
                <div class="row">
                    <div class="col l-3 footer-content__logo">
                        <div class="footer-content__name-store">
                            Nhóm 1 - C1022MĐ
                        </div>
                        <p class="footer-content__member">DUY - Mạnh - Cường - Thắng</p>
                    </div>

                    <div class="col l-3">
                        <ul class="footer__nav">
                            <li class="footer__nav-item">Giới thiệu</li>
                            <li class="footer__nav-item">Chính sách bảo mật</li>
                            <li class="footer__nav-item">Chính sách bảo hành</li>
                            <li class="footer__nav-item">Tin tức & sự kiện</li>
                        </ul>
                    </div>

                    <div class="col l-3">

                    </div>

                    <div class="col l-3">

                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
