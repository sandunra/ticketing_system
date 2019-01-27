<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

	<h2>List of Projects</h2>
	<table>
		<td>ID</td><td>TITLE</td><td>DESCRIPTION</td><td>TYPE</td><td>CLIENT</td>
		<tr>
		</tr>
		<c:forEach items="${projects}" var="project">
			<tr>
				<td>${project.id}</td>
				<td><a href="<c:url value='/project/edit-${project.id}' />">${project.title}</a></td>
				<td>${project.description}</td>
				<td>${project.type}</td>
				<td>${project.client}</td>&nbsp;&nbsp;&nbsp;
				<td><a href="<c:url value='/project-${project.id}/task-list' />">View Task List</a></td>
				<td><a href="<c:url value='/project/delete-${project.id}' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/project/new' />">Add New Project</a>
</body>
</html>