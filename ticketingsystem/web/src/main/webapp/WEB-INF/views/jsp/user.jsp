<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<% if(session.getAttribute("username") == null)
		response.sendRedirect("/");
	%>
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
<div class="bg-img">
<jsp:include page="usermenu.jsp" />
	<h2>Dear <%= session.getAttribute("username")%>, Welcome to User Page**********</h2>
	<%--<a href="<c:url value="/logout" />">Logout</a>--%>
</div>
</body>
</html>