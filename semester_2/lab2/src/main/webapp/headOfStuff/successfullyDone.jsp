<%--
  Created by IntelliJ IDEA.
  User: art
  Date: 5/31/20
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Done!</title>
</head>
<body>
<h1>Successfully done!!!</h1>
    <%if (request.getParameter("login") != null) {%>
        <div style="padding-left: 5px; padding-top: 5px">
            Login : <%=request.getParameter("login")%>
        </div>
    <%}%>
    <%if (request.getParameter("passwd") != null) {%>
        <div style="padding-left: 5px; padding-top: 5px">
            Password : <%=request.getParameter("passwd")%>
        </div>
    <%}%>
<div style="padding-left: 5px; padding-top: 5px">
    <br/>
    <a href="http://localhost:8080/lab2_war_exploded/headOfStuffMainPage">Return to head of stuff main page</a>
</div>
<div style="padding-left: 5px; padding-top: 5px">
    <br/>
    <a href="http://localhost:8080/lab2_war_exploded/">Return to main</a>
</div>
</body>
</html>

