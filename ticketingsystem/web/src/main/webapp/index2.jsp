<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zxx">

<head>
	<title>Ticketing System</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content="Ticketing System,BeyondM, Task Assigning System , Hsenid Mobile Solutions,Employee Managemen,Spring MVC,Hibernate,News letter Forms,Elements"
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
	<link href="<c:url value='/resources/css/style.css' />"  rel="stylesheet" type="text/css" media="all"/>
	<!-- Style-CSS -->
	<link href="<c:url value='/resources/css/fontawesome-all.css' />" rel="stylesheet"/>
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
	<span>T</span>icketing
	<span>S</span>ystem -
	<span>B</span>eyondM</h1>
<!-- //title -->
<!-- content -->
<div class="sub-main-w3">
	<c:url var="validateUrl" value="/home" />
	<form id="user_form" action="${validateUrl}" method="post">
		<c:if test="${param.error != null}">
			<div class="alert alert-danger">
				<p style="color: red">Invalid username and password.</p>
			</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div class="alert alert-success">
				<p style="color:darkorange">You have been logged out successfully.</p>
			</div>
		</c:if>
		<div class="form-style-agile">
			<label>
				Username
				<i class="fas fa-user"></i>
			</label>
			<input placeholder="Username" name="username" id="username" type="text" required="">
		</div>
		<div class="form-style-agile">
			<label>
				Password
				<i class="fas fa-unlock-alt"></i>
			</label>
			<input placeholder="Password" name="password" type="password" required="">
		</div>
		<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />

		<!-- checkbox -->
		<%--<div class="wthree-text">
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
		</div>--%>
		<!-- //switch -->
		<input type="submit" value="Log In">
	</form>
</div>
<!-- //content -->

<!-- copyright -->
<div class="footer">
	<p>&copy; 2018 BeyondM. All Rights Reserved.  | Design by
		<a href="http://beyondm.net">BeyondM</a>
	</p>
</div>
<!-- //copyright -->

</body>

</html>
