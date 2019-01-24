<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin page</title>

	<style>
		a{
			padding-left: 10px;
			padding-right: 10px;
		}
	</style>
</head>
<body>
<jsp:include page="menu.jsp" />
	Dear <strong>${pageContext.request.userPrincipal.name}</strong>, Welcome to User Page.
	<%--<a href="<c:url value="/logout" />">Logout</a>--%>
</body>
</html>