<%--
  Created by IntelliJ IDEA.
  User: art
  Date: 5/30/20
  Time: 11:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String login_msg=(String)request.getAttribute("error");
    if(login_msg!=null)
        out.println("<font color=red size=4px>"+login_msg+"</font>");
%>
<html>
<head>
    <title>Логін для судді</title>
</head>
<body>
<form action="checkPasswordJudge" method="post">
    <h2>Login</h2>
    <div class="container">
        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="uname" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit">Login</button>
    </div>
    <div style="padding-left: 5px; padding-top: 5px">
        <br/>
        <a href="http://localhost:8080/lab2_war_exploded/">Return to main</a>
    </div>

</form>
</body>
</html>
