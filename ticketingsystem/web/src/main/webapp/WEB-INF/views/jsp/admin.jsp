<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ticketing System</title>
	<style>
		a{
			padding-left: 10px;
			padding-right: 10px;
		}
	</style>
</head>
<body>
<jsp:include page="adminmenu.jsp" />
	Dear <strong>${user}</strong>, Welcome to Admin Page--------------------------------.
	<%--<a href="<c:url value="/logout" />">Logout</a>--%>
</body>
</html>