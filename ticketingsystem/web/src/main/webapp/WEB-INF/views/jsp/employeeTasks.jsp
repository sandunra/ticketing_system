<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ticketing System</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
<jsp:include page="adminmenu.jsp" />

	<h2>List of Tasks</h2>
	<table>
		<td>ID</td><td>TITLE</td><td>PROJECT</td><td>DESCRIPTION</td><td>ASSIGNED HOURS</td><td>COMMENT</td><td>SPENT HOURS</td><td>STATUS</td><td></td>
		<tr>
		</tr>
		<c:forEach items="${tasks}" var="task">
			<tr>
				<td><a href="<c:url value='/task-${task.id}/edit' />">${task.id}</a></td>
				<td>>${task.title}</td>
				<td>${task.project.title}</td>
				<td>${task.description}</td>
				<td>>${task.assignedHours}</td>
				<td>${task.comment}</td>
				<td>${task.spentHours}</td>
				<td>${task.status}</td>
				<td><a href="<c:url value='/task-${task.id}/delete' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/task/new' />">Add New Task</a>
</body>
</html>