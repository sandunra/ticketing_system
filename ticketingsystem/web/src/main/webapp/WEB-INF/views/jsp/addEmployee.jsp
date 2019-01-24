<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Employee Registration Form</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<c:choose>
		<c:when test="${edit}">
			<h2>Edit Employee</h2>
		</c:when>
		<c:otherwise>
			<h2>Add Employee</h2>
		</c:otherwise>
	</c:choose>

	<form:form method="POST" modelAttribute="employee">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="name">Name: </label> </td>
				<td><form:input path="name" id="name" name="name"/></td>
				<td><form:errors path="name" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="email">Email: </label> </td>
				<td><form:input path="email" id="email" name ="email"/></td>
				<td><form:errors path="email" cssClass="error"/></td>
		    </tr>

			<tr>
				<td><label for="role">Role: </label> </td>
				<td>
						<form:select path="role" id="role" name="role">
							<c:forEach items="${roleList}" var="role">
								<option value="${role.id}" >${role.title}</option>
							</c:forEach>

							<%--<form:options items="${roleList}" />--%>
						</form:select>
				</td>
				<td><form:errors path="role" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="email">Username: </label> </td>
				<td><form:input path="username" id="email" name="username"/></td>
				<td><form:errors path="username" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="email">Password: </label> </td>
				<td><form:password path="password" id="password" name="password"/></td>
				<td><form:errors path="password" cssClass="error"/></td>
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
	Go back to <a href="<c:url value='/employee/list' />">List of All Employees</a>
</body>
</html>