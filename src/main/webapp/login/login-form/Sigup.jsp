
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="SigupForm.css">
</head>
<body>
<div>
  <form action="" >
    <div class="container">
      <%--@declare id="email"--%><%--@declare id="psw"--%><%--@declare id="psw-repeat"--%><%--@declare id="username"--%><%--@declare id="phone-number"--%><%--@declare id="address"--%><h1>Form Đăng Ký</h1>
      <p style="color: white">Xin hãy nhập biểu mẫu bên dưới để đăng ký.</p>
      <hr>
      <label for="username"><b>User name</b></label>
      <input type="text" placeholder="Nhập tên đăng nhập" name="name"  required>

      <label for="psw"><b>Mật Khẩu</b></label>
      <input type="password" placeholder="Nhập Mật Khẩu" name="psw" required>

      <label for="psw-repeat"><b>Nhập Lại Mật Khẩu</b></label>
      <input type="password" placeholder="Nhập Lại Mật Khẩu" name="psw-repeat" required>

      <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Nhập Email" name="email" required>

      <label for="phone-number"><b>số điện thoại</b></label>
      <input type="text" placeholder="Nhập số điện thoại" name="phone" required>

      <label for="address"><b>địa chỉ </b></label>
      <input type="text" placeholder="Nhập địa chỉ" name="address" required>
      <label>
        <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Nhớ Đăng Nhập
      </label>

      <div class="clearfix">
        <button type="submit" class="signupbtn">Sign Up</button>
      </div>
    </div>
  </form>
</div>
</body>
</html>
