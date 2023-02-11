
<!DOCTYPE HTML>
<html>
<head>
	<script src="js/jquery.min.js"></script>
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Classy Login form Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
	<link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700' rel='stylesheet' type='text/css'>
</head>
<body >
<!--header start-->
<div class="header">
	<div class="header-main">
		<div id="form-main" action="/AccountServlet" >
			<h1> Login Form</h1>
			<div class="header-bottom">
				<div class="header-right w3agile">
					<div class="header-left-bottom agileinfo">
						<form action="/AccountServlet" method="post">
							<input type="text"  value="User name" name="username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User name';}"/>
							<input type="password"  value="Password" name="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}"/>
							<div class="remember">
			             <span class="checkbox1">
							   <label class="checkbox"><input type="checkbox" name="" checked=""><i> </i>Remember me</label>
						 </span>
								<div class="clear"> </div>
							</div>
<%--							<input type="submit" value="Login">--%>
<%--							<input type="submit" value="create" name="sigup" >--%>
						</form>
						<a href="/AccountServlet" id="login"> LOG IN </a>
						<a href="Sigup.jsp" id="create"> CREATE </a>
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
</html>