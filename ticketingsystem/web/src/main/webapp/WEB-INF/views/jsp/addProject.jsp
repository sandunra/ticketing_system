<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add New Project</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>Add/Edit Project</h2>
 
	<form:form method="POST" modelAttribute="project">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="title">Title: </label> </td>
				<td><form:input path="title" id="title"/></td>
				<td><form:errors path="title" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="description">Description: </label> </td>
				<td><form:input path="description" id="description"/></td>
				<td><form:errors path="description" cssClass="error"/></td>
		    </tr>

			<tr>
				<td><label for="type">Type: </label> </td>
				<td><form:select path="type" id="type">
					<form:option value = "NONE" label = "Select"/>
					<form:options items="${appTypeList}"/>
				</form:select></td>
				<td><form:errors path="type" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="client">Client: </label> </td>
				<td><form:input path="client" id="client"/></td>
				<td><form:errors path="client" cssClass="error"/></td>
			</tr>
	
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Add"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/project/list' />">List of All Projects</a>
</body>
</html>