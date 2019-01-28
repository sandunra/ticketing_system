<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>
<html>
<head>
	<% if(session.getAttribute("username") == null)
		response.sendRedirect("/");
	%>
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
<div class="bg-img">
<jsp:include page="adminmenu.jsp" />

	<h2>List of Tasks</h2>
	<table class="customizetable">
		<th>ID</th><th>TITLE</th><th>PROJECT</th><th>DESCRIPTION</th><th>ASSIGNED HOURS</th><th>COMMENT</th><th>SPENT HOURS</th><th>STATUS</th><th></th>
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
</div>
</body>
</html>