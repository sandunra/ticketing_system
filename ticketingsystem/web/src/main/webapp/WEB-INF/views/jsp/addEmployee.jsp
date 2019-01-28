<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<% if(session.getAttribute("username") == null)
			response.sendRedirect("/");
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ticketing System</title>
	<link href="<c:url value="/resources/css/mystyles.css" />" rel="stylesheet">

</head>

<body>
<div class="bg-img">
	<jsp:include page="adminmenu.jsp" />

	<h2>Add Employee</h2>

	<form:form method="POST" modelAttribute="employee">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="name">Name: </label> </td>
				<td><form:input path="name" id="name" name="name" required="true" placeholder="Employee name"/></td>
				<td><form:errors path="name" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="email">Email: </label> </td>
				<td><form:input path="email" id="email" name ="email" required="true" placeholder="Employee email"/></td>
				<td><form:errors path="email" cssClass="error"/></td>
		    </tr>

			<tr>
				<td><label for="role">Role: </label> </td>
				<td>
						<form:select path="role.id" id="role" name="role" required="true">
							<option value=0 >--- Select Role ---</option>
							<c:forEach items="${roleList}" var="role">
								<option value="${role.id}" ${role.id == employee.role.id ? 'selected="selected"' : ''}>${role.title}</option>
							</c:forEach>

							<%--<form:options items="${roleList}" />--%>
						</form:select>
				</td>
				<td><form:errors path="role" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="email">Username: </label> </td>
				<td><form:input path="username" id="username" name="username" required="true" placeholder="Employee username"/></td>
				<td><form:errors path="username" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="email">Password: </label> </td>
				<td><form:password path="password" id="password" name="password" required="true" placeholder="Employee password"/></td>
				<td><form:errors path="password" cssClass="error"/></td>
			</tr>
	
			<tr>
				<td colspan="3">
					<input type="submit" value="Add"/>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/employee/list' />">List of All Employees</a>

</div>
</body>
</html>