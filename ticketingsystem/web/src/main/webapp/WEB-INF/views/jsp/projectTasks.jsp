<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
		body {font-family: Arial, Helvetica, sans-serif;}
		* {box-sizing: border-box;}

		/* Button used to open the contact form - fixed at the bottom of the page */
		.open-button {
			background-color: #555;
			color: white;
			padding: 16px 20px;
			border: none;
			cursor: pointer;
			opacity: 0.8;
		}

		/* The popup form - hidden by default */
		.form-popup {
			display: none;
			position: absolute;
			border: 3px solid #f1f1f1;
			z-index: 9;
		}

		/* Add styles to the form container */
		.form-container {
			max-width: 300px;
			padding: 10px;
			background-color: white;
		}

		/* Full-width input fields */
		.form-container input[type=text], .form-container input[type=password] {
			width: 100%;
			padding: 15px;
			margin: 5px 0 22px 0;
			border: none;
			background: #f1f1f1;
		}

		/* When the inputs get focus, do something */
		.form-container input[type=text]:focus, .form-container input[type=password]:focus {
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
				<c:when test="${task.status==0}">
					<% status="Not Assigned yet"; %>
					<br />
				</c:when>
				<c:when test="${task.status==1}">
					<% status="Assigned";%>
					<br />
				</c:when>
				<c:when test="${task.status==0}">
					<% status = "Ongoing"; %>
					<br />
				</c:when>
				<c:when test="${task.status==0}">
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
				<td><button class="open-button" onclick="openForm()">Open Form</button></td>
			</tr>
		</c:forEach>
	</table>
	<br/>

	<div class="form-popup" id="myForm">
		<form action="/action_page.php" class="form-container">
			<h1>Login</h1>

			<label for="email"><b>Email</b></label>
			<input type="text" placeholder="Enter Email" name="email" required>

			<label for="psw"><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="psw" required>

			<button type="submit" class="btn">Login</button>
			<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
		</form>
	</div>

	<script>
		function openForm() {
			document.getElementById("myForm").style.display = "block";
		}

		function closeForm() {
			document.getElementById("myForm").style.display = "none";
		}
	</script>


	<a href="<c:url value='/project-${project.id}/task/new' />">Add New Task</a>
</body>
</html>