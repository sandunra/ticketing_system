<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>
<% Integer myId = (Integer)session.getAttribute("id") ;%>
<html>
<head>
	<% if(session.getAttribute("username") == null)
		response.sendRedirect("/");
	%>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Ticketing System</title>
	<link href="<c:url value="/resources/css/popUpFormStyles.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/mystyles.css" />" rel="stylesheet">
</head>

<script>
	if(${showpopup}){
		window.onload = function() {
			document.getElementById("myForm").style.display = "block";
			if(${error})
			document.getElementById("error").style.display = "block";
		}
	}
</script>

<script>

	if(${showreversepopup}){
		window.onload = function() {
			document.getElementById("reverseAssignTaskForm").style.display = "block";
			if(${error})
				document.getElementById("reasonError").style.display = "block";
		}
	}
</script>

<body>
<div class="bg-img">
<jsp:include page="adminmenu.jsp" />

	<h2>${project.title}  Project Task List</h2>

	</hr>

	<table class="customizetable">
		<th>TASK ID</th><th>TITLE</th><th>DESCRIPTION</th><th>ASSIGNED HOURS</th><th>ASSIGNEE</th><th>COMMENT</th><th>SPENT HOURS</th><th>STATUS</th><th></th><th></th>
		<tr>
		</tr>

		<c:choose>
			<c:when test="${noTasks}">
				<p>Tere is no any task related with ${project.title} project.</p>
			</c:when>
		</c:choose>

		<c:forEach items="${tasks}" var="task">
			<%! String status;%>
			<%! String assignStatus;%>
			<%! String bgcolor;%>
			<%! String url;%>

			<c:choose>
				<c:when test="${task.status==1}">
					<% status="Not Assigned yet"; %>
					<% assignStatus="Assign Task"; %>
					<% bgcolor="blue"; %>
					<% url="/project-${project.id}/task-${task.id}/assign"; %>
				</c:when>
				<c:when test="${task.status==2}">
					<% status="Assigned";%>
					<% assignStatus="Reverse Assign"; %>
					<% bgcolor="red"; %>

				</c:when>
				<c:when test="${task.status==3}">
					<% status = "Ongoing"; %>
					<% assignStatus="Reverse Assign"; %>
					<% bgcolor="red"; %>

				</c:when>
				<c:when test="${task.status==4}">
					<% status = "Terminate"; %>
					<% assignStatus="Assign ask"; %>
					<% bgcolor="blue"; %>

				</c:when>
				<c:otherwise>
					<% status = "Complete"; %>
					<% assignStatus="Confirm"; %>
					<% bgcolor="green"; %>

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
				<td>
					<div class="my_content_container" id="assign">
						<div class="<%=bgcolor%>">

							<c:choose>
								<c:when test="${task.status==2 || task.status==3 }">
									<a href="<c:url value='/project-${project.id}/task-${task.id}/reverse-assign' />"><%=assignStatus%></a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/project-${project.id}/task-${task.id}/assign' />"><%=assignStatus%></a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
						<%--<button class="open-button" onclick="openForm( ${task.id} , ${task.assignedHours})">Assign Task
                        </button>--%>
				</td>
				<td>
					<div class="my_content_container">
						<div class="red">
							<a href="<c:url value='/project-${project.id}/task-${task.id}/delete' />" onclick="return confirm('Are you sure you want to delete this task?');">delete</a>
						</div>
					</div>
				</td>

			</tr>
		</c:forEach>
	</table>
	<br/>
	<% Integer Id;%>

	<div class="form-popup" id="myForm" style="display: none">

		<form id="formAssignTask" modelAttribute="task" class="form-container" method="post" role="form">
			<input type="hidden" path="id" name = "id" id="id"/>
			<label for="assignee">Assignee</label>
			<select id="assignee" name="assignee" path="assignee">
				<option value=0 >--- Select Assignee ---</option>
				<c:forEach var="employee" items="${employeeList}">
					<option value="${employee.id}">${employee.name}</option>
				</c:forEach>
			</select>
			<div id="error" style="display: none; color: #ff0000">
				*Please select assignee here..
			</div>

			</br>

			<label for="assignHours">Assigned Hours</label>
			<input type="number" value="${task.assignedHours}" placeholder="Enter Assign hours for task" id="assignHours" name="assignHours" required>

			<button type="submit" class="btn" onsubmit="validate()">Assign Task</button>
			<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
		</form>
	</div>

	<div class="form-popup" id="reverseAssignTaskForm" style="display: none">

		<form id="formReverseAssignTask" modelAttribute="task" class="form-container" method="post" role="form">
			<input type="hidden" path="id" name = "id" id="taskId"/>
			<label for="reason">Reason to Reverse Assign Task</label>
			<textarea id="reason" name="reason" class="form-container" required="required" style="min-height: 100px;"></textarea>
			<div id="reasonError" style="display: none; color: #ff0000">
				*Please mention the reason for reverse assign task..
			</div>

			</br>

			<button type="submit" class="btn" onsubmit="validate()">Reverse Assign Task</button>
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
			document.getElementById("reverseAssignTaskForm").style.display = "none";
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
</div>
</body>
</html>