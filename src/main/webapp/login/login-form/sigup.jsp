
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="sigupForm.css">
  <script>
    function validatePassword() {
      var pass= document.getElementById("password").value;
      var repeat = document.getElementById("pswrepeat").value;
      if (pass != repeat) {
        alert("psw-repeatPasswords do not match.");
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
<div>
  <form action="/view?action=create" method="post" >
    <div class="container">
      <%--@declare id="email"--%><%--@declare id="psw"--%><%--@declare id="psw-repeat"--%><%--@declare id="username"--%><%--@declare id="phone-number"--%><%--@declare id="address"--%><%--@declare id="pswrepeat"--%><h1>Form Đăng Ký</h1>
      <p style="color: white">Xin hãy nhập biểu mẫu bên dưới để đăng ký.</p>
      <hr>
      <label for="username"><b>User name</b></label>
      <input type="text" placeholder="Nhập tên đăng nhập" name="name"  required>

      <label for="psw"><b>Mật Khẩu</b></label>
      <input type="password" placeholder="Nhập Mật Khẩu" name="psw" id="password" required>

      <label for="pswrepeat"><b>Nhập Lại Mật Khẩu</b></label>
      <input type="password" placeholder="Nhập Lại Mật Khẩu" name="pswrepeat" id="pswrepeat" required>

      <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Nhập Email" name="email" required>

      <label for="phone-number"><b>Số điện thoại</b></label>
      <input type="text" placeholder="Nhập số điện thoại" name="phone" required>

      <label for="address"><b>Địa chỉ </b></label>
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
