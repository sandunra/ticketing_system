<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String username;
  Integer userId;
  if (session != null) {
    if (session.getAttribute("username") != null) {
      username = (String) session.getAttribute("username");
      userId = (Integer) session.getAttribute("id");
      Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

     // out.print("Hello, " + username + "  Welcome to ur Profile - " + isAdmin + " : " +userId);
    } else {
      response.sendRedirect("/");
    }
  }
%>

<% if(session.getAttribute("username") == null)
  response.sendRedirect("/");
%>

<link href="<c:url value="/resources/css/topNavigationStyles.css" />" rel="stylesheet">


<div class="topnav">
  <a class="active" href="${pageContext.request.contextPath}/home/">Home</a>
  <a href="${pageContext.request.contextPath}/empId-<%= session.getAttribute("id")%>/assignTasks/">Assigned Tasks</a>
  <div class="topnav-right">
    <a href="#"><%= session.getAttribute("username")%></a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
  </div>
</div>


<%--<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">

  <a href="${pageContext.request.contextPath}/empId-<%= session.getAttribute("id")%>/assignTasks">Assigned Tasks</a>
  | &nbsp;
&lt;%&ndash;
  <a href="${pageContext.request.contextPath}/employee/">Employee</a>
  | &nbsp;
&ndash;%&gt;

  &lt;%&ndash;<c:if test="${pageContext.request.userPrincipal.name != null}">&ndash;%&gt;
     &nbsp;
    <a href="${pageContext.request.contextPath}/logout">Logout</a>

  &lt;%&ndash;</c:if>&ndash;%&gt;

</div>--%>
