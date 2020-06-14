<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String login_msg=(String)request.getAttribute("error");
    if(login_msg!=null)
        out.println("<font color=red size=4px>"+login_msg+"</font>");
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="checkPassword" method="post">
    <h2>Login</h2>
    <div class="container">
        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="uname" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit">Login</button>
    </div>
    <div>Create new account</div>
</form>
</body>
</html>
