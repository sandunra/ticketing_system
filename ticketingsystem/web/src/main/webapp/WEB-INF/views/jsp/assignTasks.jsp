<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>

<html>
<head>
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

<body>
<jsp:include page="usermenu.jsp" />

<%--<h2>${project.title}  Assign Task List</h2>--%>

</hr>

<table>
	<td>PROJECT</td><td>TASK</td><td>DESCRIPTION</td><td>ASSIGNED HOURS</td><td>COMMENT</td><td>STATUS</td>
	<tr>
	</tr>
	<%Integer userId = (Integer) session.getAttribute("id");%>
	<c:forEach items="${tasks}" var="task">
		<%! String status;%>
		<%! String assignStatus;%>
		<%! String bgcolor;%>

		<c:choose>
			<c:when test="${task.status==1}">
				<% status="Not Assigned yet"; %>
				<% assignStatus=""; %>
				<% bgcolor="blue"; %>
			</c:when>
			<c:when test="${task.status==2}">
				<% status="Assigned";%>
				<% assignStatus="Update Status"; %>
				<% bgcolor="green"; %>

			</c:when>
			<c:when test="${task.status==3}">
				<% status = "Ongoing"; %>
				<% assignStatus="Update Status"; %>
				<% bgcolor="green"; %>

			</c:when>
			<c:when test="${task.status==4}">
				<% status = "Terminate"; %>
				<% assignStatus="Update Status"; %>
				<% bgcolor="green"; %>

			</c:when>
			<c:otherwise>
				<% status = "Complete"; %>
				<% assignStatus="Update Status"; %>
				<% bgcolor="green"; %>

				<br />
			</c:otherwise>
		</c:choose>

		<tr>
			<td>${task.project.title}</td>
			<td>${task.title}</td>
			<td>${task.description}</td>
			<td>${task.assignedHours}</td>
			<td>${task.comment}</td>
			<td><%=status%></td>
			<td>
				<div class="my_content_container" id="assign">
					<div class="<%=bgcolor%>">
						<a href="${pageContext.request.contextPath}/emp-<%= session.getAttribute("id")%>/task-${task.id}/update"><%=assignStatus%></a>
					</div>
				</div>
					<%--<button class="open-button" onclick="openForm( ${task.id} , ${task.assignedHours})">Assign Task
                    </button>--%>
			</td>
			<%--<td>
				<div class="my_content_container">
					<a href="<c:url value='/project/task-${task.id}/delete' />">delete</a>
				</div>
			</td>--%>

		</tr>
	</c:forEach>
</table>
<br/>
<% Integer Id;%>

<div class="form-popup" id="myForm" style="display: none">

	<form id="formAssignTask" modelAttribute="task" class="form-container" method="post" role="form">
		<input type="hidden" path="id" name = "id" id="id"/>
		<label for="status">Status</label>
		<select id="status" name="status" path="status">
			<option value=0 disabled selected>--- Select status ---</option>
			<option value=3 >Ongoing</option>
			<option value=4 >Terminate</option>
			<option value=5 >Complete</option>

			<%--<c:forEach var="employee" items="${employeeList}">
				<option value="${employee.id}">${employee.name}</option>
			</c:forEach>--%>
		</select>
		<div id="error" style="display: none; color: #ff0000">
			*Please select status here..
		</div>

		</br>

		<label for="spentHours">Spent Hours</label>
		<input type="number" value="${task.spentHours}" min="0" placeholder="Enter spent hours for this task" id="spentHours" name="spentHours" required>

		<label for="comment">Comment</label>
		<textarea path="comment" id="comment" name="comment" placeholder="Any comment you can add here.."></textarea>

		<div style="height: 5px"></div>

		<button type="submit" class="btn" onsubmit="validate()">Updae Status</button>
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


<%--
<a href="<c:url value='/project-${project.id}/task/new' />">Add New Task</a>
--%>
</body>
</html>