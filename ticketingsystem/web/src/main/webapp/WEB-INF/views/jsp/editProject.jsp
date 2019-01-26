<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ticketing System</title>
	<link href="<c:url value="/resources/css/mystyles.css" />" rel="stylesheet">

</head>

<body>

	<h2>Edit Project</h2>
 
	<form:form method="POST" modelAttribute="project">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="title">Title: </label> </td>
				<td><form:input path="title" id="title" placeholder="Project Title.." required="true"/></td>
				<td><form:errors path="title" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="description">Description: </label> </td>
				<td><form:textarea path="description" id="description" placeholder="Add descripton about project here.."/></td>
				<td><form:errors path="description" cssClass="error"/></td>
		    </tr>

			<tr>
				<td><label for="type">Type: </label> </td>
				<td>
					<form:select path="type" id="type">
						<form:option value = "NONE" label = "--- Select Project Type ---"/>
						<form:options items="${appTypeList}"/>
					</form:select>
				</td>
				<td><form:errors path="type" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="client">Client: </label> </td>
				<td><form:input path="client" id="client" placeholder="Project Client" required="true"/></td>
				<td><form:errors path="client" cssClass="error"/></td>
			</tr>
	
			<tr>
				<td colspan="3">
					<input type="submit" value="Update"/>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/project/list' />">List of All Projects</a>
</body>
</html>