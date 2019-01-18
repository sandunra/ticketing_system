<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Ticketing System :: BEYONDM</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content="Classic Sign In Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements"
			/>
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<!-- Meta tag Keywords -->
	<!-- css files -->
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<link rel="stylesheet" href="css/fontawesome-all.css">
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //css files -->
	<!-- web-fonts -->
	<link href="//fonts.googleapis.com/css?family=Reem+Kufi&amp;subset=arabic" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
	<!-- //web-fonts -->
</head>

<body>
<!-- title -->
<h1>
	<span>C</span>lassic
	<span>S</span>ign
	<span>I</span>n
	<span>F</span>orm</h1>
<!-- //title -->
<!-- content -->
<div class="sub-main-w3">
	<form id="user_form" action="${validateUrl}" method="POST">
		<div class="form-style-agile">
			<label>
				Username
				<i class="fas fa-user"></i>
			</label>
			<input placeholder="Username" name="Name" type="text" required="">
		</div>
		<div class="form-style-agile">
			<label>
				Password
				<i class="fas fa-unlock-alt"></i>
			</label>
			<input placeholder="Password" name="Password" type="password" required="">
		</div>
		<!-- checkbox -->
		<div class="wthree-text">
			<ul>
				<li>
					<!-- switch -->
					<div class="checkout-w3l">
						<div class="demo5">
							<div class="switch demo3">
								<input type="checkbox">
								<label>
									<i></i>
								</label>
							</div>
						</div>
						<a href="#">Stay Signed In</a>
					</div>
					<!-- //checkout -->
				</li>
				<li>
					<a href="#">Forgot Password?</a>
				</li>
			</ul>
		</div>
		<!-- //switch -->
		<button id="confirm_user" type="submit" value="Log In"></button>
	</form>
</div>
<!-- //content -->

<!-- copyright -->
<div class="footer">
	<p>&copy; 2018 Classic Sign In Form. All rights reserved | Design by
		<a href="http://beyondm.net">BeyondM</a>
	</p>
</div>
<!-- //copyright -->

</body>

</html>