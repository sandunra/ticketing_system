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

	<h2>Add/Edit Task</h2>
 
	<form:form method="POST" modelAttribute="task" action="/project-${id}/task-list">"
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="title">Title: </label> </td>
				<td><form:input path="title" id="title" name="title"/></td>
				<td><form:errors path="title" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="description">Description: </label> </td>
				<td><form:input path="description" id="description" name="description"/></td>
				<td><form:errors path="description" cssClass="error"/></td>
		    </tr>

			<tr>
				<td><label for="project">Project: </label> </td>
				<td><form:select path="project" id="project" name="project">
					<c:forEach items="${projectList}" var="projects">
						<option value="${projects.id}" >${projects.title}</option>
					</c:forEach>
				</form:select></td>
				<td><form:errors path="project" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="employee">Assignee: </label> </td>
				<td><form:select path="employee" id="employee" name="employee">
					<c:forEach items="${employeeList}" var="employee">
						<option value="${employee.id}" >${employee.name}</option>
					</c:forEach>
				</form:select></td>
				<td><form:errors path="employee" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="assignedHours">Assigned Hours: </label> </td>
				<td><form:input path="assignedHours" id="assignedHours" name="assignedHours"/></td>
				<td><form:errors path="assignedHours" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="spentHours">Assign Hours: </label> </td>
				<td><form:input path="spentHours" id="spentHours" name="spentHours"/></td>
				<td><form:errors path="spentHours" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="comment">Comment: </label> </td>
				<td><form:textarea path="comment" id="comment" name="comment"/></td>
				<td><form:errors path="comment" cssClass="error"/></td>
			</tr>

			<tr>
				<td><label for="status">Status: </label> </td>
				<td><form:select path="status" id="status" name="status">
					<form:option value = '0' label = "Select"/>
					<form:option value='1' label="Ongoing"/>
					<form:option value='2' label="Complete"/>
				</form:select></td>
				<td><form:errors path="status" cssClass="error"/></td>
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

	Go back to
	<c:choose>
		<c:when test="${admin}">
			<a href="<c:url value='/project-${id}/task-list' />">List of Project Tasks</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/project-${id}/task-list' />">List of All Tasks</a>
		</c:otherwise>
	</c:choose>
</body>
</html>