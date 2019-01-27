<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ticketing System</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
		a{
			padding-left: 10px;
			padding-right: 10px;
		}
	</style>

</head>

<body>
<jsp:include page="adminmenu.jsp" />

	<h2>List of Employees</h2>	
	<table>
		<tr>
			<td>ID</td><td>NAME</td><td>EMAIL</td><td>ROLE</td><td></td>
		</tr>
		<c:forEach items="${employees}" var="employee">
			<tr>
			<td><a href="<c:url value='/employee/edit-${employee.id}' />">${employee.id}</a></td>
			<td>${employee.name}</td>
			<td>${employee.email}</td>
			<td>${employee.role.title}</td>
			<td><a href="<c:url value='/employee/delete-${employee.id}' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/employee/new' />">Add New Employee</a>
</body>
</html>