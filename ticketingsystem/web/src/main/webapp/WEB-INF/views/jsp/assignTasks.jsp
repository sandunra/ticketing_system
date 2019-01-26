<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ticketing System</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

	<script>
		$(function() {

			$("#dialog").dialog({
				autoOpen: false,
				modal: true
			});

			$("#myButton").on("click", function(e) {
				e.preventDefault();
				$("#dialog").dialog("open");
			});

		});
	</script>

</head>

<body>
<jsp:include page="menu.jsp" />

	<h2>${project.title}  Project Task List</h2>

	</hr>

	<table>
		<td>TASK ID</td><td>TITLE</td><td>DESCRIPTION</td><td>ASSIGNED HOURS</td><td>ASSIGNEE</td><td>COMMENT</td><td>SPENT HOURS</td><td>STATUS</td>
		<tr>
		</tr>
		<c:forEach items="${tasks}" var="task">
			<%! String status;%>

			<c:choose>
				<c:when test="${task.status==1}">
					<% status="Not Assigned yet"; %>
					<br />
				</c:when>
				<c:when test="${task.status==2}">
					<% status="Assigned";%>
					<br />
				</c:when>
				<c:when test="${task.status==3}">
					<% status = "Ongoing"; %>
					<br />
				</c:when>
				<c:when test="${task.status==4}">
					<% status = "Terminate"; %>
					<br />
				</c:when>
				<c:otherwise>
					<% status = "Complete"; %>
					<br />
				</c:otherwise>
			</c:choose>

			<tr>
				<td><a href="<c:url value='/task-${task.id}/edit' />">${task.id}</a></td>
				<td>${task.title}</td>
				<td>${task.description}</td>
				<td>${task.assignedHours}</td>
				<td>${task.employee.name}</td>
				<td>${task.comment}</td>
				<td>${task.spentHours}</td>
				<td><%=status%></td>
				<td><a href="<c:url value='/task-${task.id}/delete' />">delete</a></td>
				<button id="myButton">Assign Task</button>

				<div id="dialog" title="Dialog box">
					<form:form method="POST" modelAttribute="employee">
						<form:input type="hidden" path="id" id="id"/>
						<table>

								<td><label for="user">Assignee: </label> </td>
								<td>
									<form:select path="user" id="user" name="user">
										<c:forEach items="${employeeList}" var="employee">
											<option value="${employee.id}" >${employee.name}</option>
										</c:forEach>
									</form:select>
								</td>
								<td><form:errors path="role" cssClass="error"/></td>
							</tr>

							<tr>
								<td><label for="assignHours">Assign hours: </label> </td>
								<td><form:input path="assignHours" id="assignHours" name="assignHours" type="number"/></td>
								<td><form:errors path="assignHours" cssClass="error"/></td>
							</tr>

							<tr>
								<td colspan="3">
									<input type="submit" value="send Mail"/>
								</td>
							</tr>
						</table>
					</form:form>
				</div>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/project-${project.id}/task/new' />">Add New Task</a>
</body>
</html>