<%--
  Created by IntelliJ IDEA.
  User: art
  Date: 6/14/20
  Time: 11:01 PM
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
    <title>Create account</title>
</head>
<body>

<form action="createAccount" method="post">
    <h2>Login</h2>
    <div class="container">
        <div style="padding: 2px">
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="uname" required>
        </div>
        <div style="padding: 2px">
            <label for="email"><b>Email:</b></label>
            <input type="email" placeholder="Enter email" name="email" required>
        </div>
        <div style="padding: 2px">
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required>
        </div>
        <div style="padding: 2px">
            <label for="psw_r"><b>Repeat password</b></label>
            <input type="password" placeholder="Repeat Password" name="psw_r" required>
        </div>
        <div style="padding: 2px">
            <select name="user_type">
                <option value="client">client</option>
                <option value="tour_agent">tour agent</option>
            </select>
        </div>
        <div style="padding: 2px">
            <button type="submit">Login</button>
        </div>
    </div>
</form>
</body>
</html>
