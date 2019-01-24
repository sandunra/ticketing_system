<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login page</title>
	<link href="<c:url value='/resources/css/bootstrap.css' />"  rel="stylesheet"/>
	<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<body>
<div id="mainWrapper">
	<div class="login-container">
		<div class="login-card">
			<div class="login-form">

				<!-- User input form to validate a user -->
				<c:url var="validateUrl" value="/user" />
				<form id="user_form" action="${validateUrl}" method="post" class="form-horizontal">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							<p>Invalid username and password.</p>
						</div>
					</c:if>
					<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
					</c:if>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
						<input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
					</div>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
						<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />

					<div class="form-actions">
						<input type="submit"
							   class="btn btn-block btn-primary btn-default" value="Log in">
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- copyright -->
	<div class="caption">
		<p>&copy; 2018 BeyondM. All Rights Reserved. | Design by
			<a href="http://beyondm.net">BeyondM</a>
		</p>
	</div>
	<!-- //copyright -->

</div>

</body>
</html>