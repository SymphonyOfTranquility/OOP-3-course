<%@ page import="TableElements.User" %><%--
  Created by IntelliJ IDEA.
  User: art
  Date: 6/15/20
  Time: 2:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("Users") == null) {
        response.sendRedirect("/lab_2_war_exploded/approveAdmin");
        return;
    }
    User[] users = (User[]) request.getAttribute("Users");
    for (int i = 0; i < users.length; ++i)
        System.out.println(users[i].toString());
%>
<html>
<head>
    <title>Approve</title>
</head>
<body>

<div>
    <h1>All tours</h1>
    <br />
</div>
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr align="center">
        <th>id</th>
        <th>username</th>
        <th>email</th>
    </tr>
    <% for (int i = 0; i < users.length; ++i) { %>
    <tr align="center">
        <%=users[i].toTable()%>
        <th align="center">
            <form action="/lab_2_war_exploded/approveAdmin?id=<%=users[i].getId()%>" method="post">
                <input type="submit" value="Approve">
            </form>
        </th>
    </tr>
    <% } %>
</table>


<div style="padding-left: 5px; padding-top: 5px">
    <br/>
    <a href="http://localhost:8080/lab_2_war_exploded/tourAgentPage">Return to main</a>
</div>

</body>
</html>
