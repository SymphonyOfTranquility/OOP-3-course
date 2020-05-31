<%@ page import="tableElements.Case" %><%--
  Created by IntelliJ IDEA.
  User: art
  Date: 5/31/20
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("Cases") == null) {
        response.sendRedirect("/lab2_war_exploded/judgeAssistantMainPage");
        return;
    }
    Case[] cases = (Case[]) request.getAttribute("Cases");
    for (int i = 0;i < cases.length; ++i)
        System.out.println(cases[i].toString());
%>
<html>
<head>
    <title>Available cases</title>
</head>
<body>
<div>
    <h1>Available cases</h1>
    <br />
</div>
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr align="center">
        <th>overall id</th>
        <th>type id</th>
        <th>plaintiff</th>
        <th>defendant</th>
        <th>theme of case</th>
        <th>is correct</th>
        <th>date of trial</th>
        <th>judgment</th>
    </tr>
    <% for (int i = 0;i < cases.length; ++i) { %>
    <tr align="center">
        <%=cases[i].toTableAssistant()%>
        <th align="center">
            <form action="/lab2_war_exploded/judgeAssistantMainPage?id=<%=cases[i].getId()%>" method="post">
                <input type="submit" value="Change">
            </form>
        </th>
    </tr>
    <% } %>
</table>


<div style="padding-left: 5px; padding-top: 5px">
    <br/>
    <a href="http://localhost:8080/lab2_war_exploded/">Return to main</a>
</div>
</body>
</html>
