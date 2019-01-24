<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Confirmation Page</title>
	<style>
		a{
			padding-left: 10px;
			padding-right: 10px;
		}
	</style>
</head>
<body>
<jsp:include page="menu.jsp" />
	message : ${success}
	<br/>
	<br/>

	Go back to
	<c:choose>
		<c:when test="${employee}">
			<a href="<c:url value='/employee/list' />">List of Employees</a>
		</c:when>
		<c:when test="${task}">
			<a href="<c:url value='/project-${id}/task-list' />">List of Tasks</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/project/list' />">List of Projects</a>
		</c:otherwise>
	</c:choose>
	
</body>

</html>