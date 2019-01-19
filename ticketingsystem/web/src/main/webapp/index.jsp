<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>User Login</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="WEB-INF/resources/css/style.css" type="text/css">

	<style type="text/css">

		html {
			-webkit-background-size: cover;
			-moz-background-size: cover;
			-o-background-size: cover;
			background-size: cover;
		}
		body, html {
			height: 100%;
		}

	</style>

</head>

<body>
<div class="container">
	<h3 id="form_header" class="text-warning" align="center">Login</h3>
	<div>&nbsp;</div>

	<div class="sub-main-w3">

		<!-- User input form to validate a user -->
		<c:url var="validateUrl" value="/user/validate" />
		<form id="user_form" action="${validateUrl}" method="POST">
			<div class="form-group">
				<label for="username">Username:</label>
				<input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
			</div>
			<button id="confirm_user" type="submit" class="btn btn-primary">Login</button>
		</form>
	</div>

	<!-- copyright -->
	<div class="footer">
		<p>&copy; 2018 BeyondM. All Rights Reserved. | Design by
			<a href="http://beyondm.net">BeyondM</a>
		</p>
	</div>
	<!-- //copyright -->
</div>
</body>
</html>