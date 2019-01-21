<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Projects</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Projects</h2>
	<table>
		<td>ID</td><td>TITLE</td><td>DESCRIPTION</td><td>TYPE</td><td>CLIENT</td><td></td>
		<tr>
		</tr>
		<c:forEach items="${projects}" var="project">
			<tr>
				<td><a href="<c:url value='/project/edit-${project.id}' />">${project.id}</a></td>
				<td><a href="<c:url value='/project-${project.id}/task-list' />">${project.title}</a></td>
				<td>${project.description}</td>
				<td>${project.type}</td>
				<td>${project.client}</td>
				<td><a href="<c:url value='/project/delete-${project.id}' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/project/new' />">Add New Project</a>
</body>
</html>