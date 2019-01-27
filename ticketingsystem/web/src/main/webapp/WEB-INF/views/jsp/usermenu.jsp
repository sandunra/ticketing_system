<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String username;
  Integer userId;
  if (session != null) {
    if (session.getAttribute("username") != null) {
      username = (String) session.getAttribute("username");
      userId = (Integer) session.getAttribute("id");
      Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

      out.print("Hello, " + username + "  Welcome to ur Profile - " + isAdmin + " : " +userId);
    } else {
      response.sendRedirect("/");
    }
  }
%>


<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">

  <a href="${pageContext.request.contextPath}/empId-<%= session.getAttribute("id")%>/assignTasks">Assigned Tasks</a>
  | &nbsp;
<%--
  <a href="${pageContext.request.contextPath}/employee/">Employee</a>
  | &nbsp;
--%>

  <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
     &nbsp;
    <a href="${pageContext.request.contextPath}/logout">Logout</a>

  <%--</c:if>--%>

</div>
