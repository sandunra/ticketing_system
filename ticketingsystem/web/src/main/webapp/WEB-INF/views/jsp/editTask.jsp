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

	<h2>Edit Task</h2>

	<form:form method="POST" modelAttribute="task" action="/project/task/edit">
		<form:input type="hidden" path="id" id="id"/>
		<form:input type="hidden" path="project.id" id="project"/>
		<table>
			<tr>
				<td><label for="title">Title: </label> </td>
				<td><form:input path="title" id="title" name="title" required="true" placeholder="Task title.."/></td>
				<td><form:errors path="title" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="description">Description: </label> </td>
				<td><form:input path="description" id="description" name="description" required="true" placeholder="Description about task.."/></td>
				<td><form:errors path="description" cssClass="error"/></td>
		    </tr>

			<%--<tr>
				<td><label for="employee">Assignee: </label> </td>
				<td>
					<form:select path="employee.id" id="employee" name="employee" required="true">
						<option value=0 >Select Employee</option>
						<c:forEach items="${employeeList}" var="employee">
							<option value="${employee.id}" ${employee.id == task.employee.id ? 'selected="selected"' : ''} > ${employee.name}</option>
						</c:forEach>
					</form:select></td>
				<td><form:errors path="employee" cssClass="error"/></td>
			</tr>--%>

			<tr>
				<td><label for="assignedHours">Assigned Hours: </label> </td>
				<td><form:input type="number" path="assignedHours" id="assignedHours" name="assignedHours" min="0" required="true" placeholder="Task title.."/></td>
				<td><form:errors path="assignedHours" cssClass="error"/></td>
			</tr>

			<%--<tr>
				<td><label for="spentHours">Spent Hours: </label> </td>
				<td><form:input path="spentHours" id="spentHours" name="spentHours"/></td>
				<td><form:errors path="spentHours" cssClass="error"/></td>
			</tr>--%>

			<tr>
				<td><label for="comment">Comment: </label> </td>
				<td><form:textarea path="comment" id="comment" name="comment" placeholder="Any comment related with task.."/></td>
				<td><form:errors path="comment" cssClass="error"/></td>
			</tr>

			<%--<tr>
				<td><label for="status">Status: </label> </td>
				<td>
					<form:select path="status" id="status" name="status" required="true">
						<c:forEach items="${taskStatusList}" var="status">
							<option value="${status.key}" ${status.key == task.status? 'selected="selected"' : ''} > ${status.value}</option>
						</c:forEach>
					</form:select>
				</td>
				<td><form:errors path="status" cssClass="error"/></td>
			</tr>--%>
	
			<tr>
				<td colspan="3">
					<input type="submit" value="Update"/>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>

	Go back to
	<c:choose>
		<c:when test="${admin}">
			<a href="<c:url value='/project-${project.id}/task-list' />">List of Project Tasks</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/project-${project.id}/task-list' />">List of All Tasks</a>
		</c:otherwise>
	</c:choose>
</body>
</html>