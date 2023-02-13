<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>

	<link href="../../assets/css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Classy Login form Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
	<link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700' rel='stylesheet' type='text/css'>
	<title></title>
</head>

<body >
<!--header start-->
<div class="header">
	<div class="header-main">
		<div id="form-main"	>
			<h1> Login Form</h1>
			<p class="error-message" style="color: white ; text-align: center ; margin-bottom: 10px">
				${messLogin}
			</p>
			<div class="header-bottom">
				<div class="header-right w3agile">
					<div class="header-left-bottom agileinfo">
						<form action="/view?action=login" method="post">
							<input type="text"  placeholder="username" name="username"/>
							<input type="password"  placeholder="Password" name="password"/>
							<div class="remember">
			             <span class="checkbox1">
							   <label class="checkbox"><input type="checkbox" name="" checked=""><i> </i>Remember me</label>
						 </span>
								<div class="clear"> </div>
							</div>

						<button id="login" type="submit"> LOGIN </button>
						</form>
						<a href="sigup.jsp" id="create"> CREATE </a>
						<br>
						<div class="header-left-top">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--header end -->

</body>
<script src="../../assets/js/jquery.min.js"></script>
</html>