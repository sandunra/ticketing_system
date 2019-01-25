<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
		body {font-family: Arial, Helvetica, sans-serif;}
		* {box-sizing: border-box;}

		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}

		/* Button used to open the contact form - fixed at the bottom of the page */
		.open-button {
			background-color: #b09168;
			color: white;
			padding: 3px 20px;
			border: none;
			cursor: pointer;
			opacity: 0.8;
			border-radius: 10px;
		}

		/* The popup form - hidden by default */
		.form-popup {
			display: none;
			border: 3px solid #f1f1f1;
			z-index: 9;
			position: absolute;
			top: 30%;
			left: 30%;
		}

		/* Add styles to the form container */
		.form-container {
			max-width: 300px;
			padding: 10px;
			background-color: white;
		}

		/* Full-width input fields */
		.form-container input[type=number], .form-container select {
			width: 100%;
			padding: 15px;
			margin: 5px 0 22px 0;
			border: none;
			background: #f1f1f1;
		}

		/* When the inputs get focus, do something */
		.form-container input[type=number], .form-container select:focus {
			background-color: #ddd;
			outline: none;
		}

		/* Set a style for the submit/login button */
		.form-container .btn {
			background-color: #4CAF50;
			color: white;
			padding: 16px 20px;
			border: none;
			cursor: pointer;
			width: 100%;
			opacity: 0.8;
		}

		.form-container label {
			color: #0b0b0b;
			padding: 26px 0px;
			border: none;
			width: 100%;
		}

		/* Add a red background color to the cancel button */
		.form-container .cancel {
			background-color: red;
		}

		/* Add some hover effects to buttons */
		.form-container .btn:hover, .open-button:hover {
			opacity: 1;
		}
	</style>

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
				<td><a href="<c:url value='/project-${project.id}/task-${task.id}/edit' />">${task.id}</a></td>
				<td>${task.title}</td>
				<td>${task.description}</td>
				<td>${task.assignedHours}</td>
				<td>${task.employee.name}</td>
				<td>${task.comment}</td>
				<td>${task.spentHours}</td>
				<td><%=status%></td>
				<td><a href="<c:url value='/project-${project.id}/task-${task.id}/delete' />">delete</a></td>
				<td><button class="open-button" onclick="openForm( ${task.id} , ${task.assignedHours})">Assign Task</button></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<% Integer Id;%>
	<div class="form-popup" id="myForm">

		<form id="formAssignTask" modelAttribute="task" class="form-container" method="post" role="form" action="<c:url value="/project/task/assign"/>">
			<input type="hidden" path="id" name = "id" id="id"/>
			<label for="assignee">Assignee</label>
			<select id="assignee" name="assignee" path="assignee">
				<option value=0 >--- Select Assignee ---</option>
				<c:forEach var="employee" items="${employeeList}">
					<option value="${employee.id}">${employee.name}</option>
				</c:forEach>
			</select>
			<input type="hidden" path="error" name = "error" id="error"/>
			</br>

			<label for="assignHours">Assigned Hours</label>
			<input type="number" value="" placeholder="Enter Assign hours for task" id="assignHours" name="assignHours" required>

			<button type="submit" class="btn" onsubmit="validate()">Assign Task</button>
			<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
		</form>
	</div>

	<script>
		function openForm(taskId, assignedHours) {
			document.getElementById("myForm").style.display = "block";
			document.getElementById('id').value = taskId;
			document.getElementById('assignHours').value = assignedHours;
		}

		function closeForm() {
			document.getElementById("myForm").style.display = "none";
			document.getElementById("myForm").onmouseout= false;
		}

		function validate() {
			var selectedAssignee = document.getElementById('assignee').value;
			if(selectedAssignee == 0){
				document.getElementById('assignee').focus();
			}else{
				document.getElementById("formAssignTask").action = "<c:url value="/project/task/assign"/>";
			}
		}
	</script>


	<a href="<c:url value='/project-${project.id}/task/new' />">Add New Task</a>
</body>
</html>