<%@ page import="tableElements.Judge" %>
<%@ page import="tableElements.JudgeAssistant" %>
<%@ page import="tableElements.Employee" %><%--
  Created by IntelliJ IDEA.
  User: art
  Date: 5/31/20
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("Judges") == null || request.getAttribute("JudgeAssistants") == null
            || request.getAttribute("Employees") == null) {
        response.sendRedirect("/lab2_war_exploded/HFDeletePerson");
        return;
    }
    Judge[] judges = (Judge[]) request.getAttribute("Judges");
    JudgeAssistant[] judgeAssistants = (JudgeAssistant[]) request.getAttribute("JudgeAssistants");
    Employee[] employees = (Employee[]) request.getAttribute("Employees");

    for (int i = 0;i < judges.length; ++i)
        System.out.println(judges[i].toString());
    for (int i = 0;i < judgeAssistants.length; ++i)
        System.out.println(judgeAssistants[i].toString());
    for (int i = 0;i < employees.length; ++i)
        System.out.println(employees[i].toString());
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Delete person</h2>
<h5>Table of judges</h5>
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr align="center">
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>login</th>
    </tr>
    <% for (int i = 0;i < judges.length; ++i) { %>
    <tr align="center">
        <%=judges[i].toTable()%>
        <th align="center">
            <form action="/lab2_war_exploded/HFDeleteAccount?type=judge&id=<%=judges[i].getId()%>" method="post">
                <input type="submit" value="Delete">
            </form>
        </th>
    </tr>
    <% } %>
</table>

<h5>Table of judge assistants</h5>
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr align="center">
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>login</th>
    </tr>
    <% for (int i = 0;i < judgeAssistants.length; ++i) { %>
    <tr align="center">
        <%=judgeAssistants[i].toTable()%>
        <th align="center">
            <form action="/lab2_war_exploded/HFDeleteAccount?type=assistant_judge&id=<%=judgeAssistants[i].getId()%>" method="post">
                <input type="submit" value="Delete">
            </form>
        </th>
    </tr>
    <% } %>
</table>


<h5>Table of employees</h5>
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr align="center">
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>profession</th>
        <th>login</th>
    </tr>
    <% for (int i = 0;i < employees.length; ++i) { %>
    <tr align="center">
        <%=employees[i].toTable()%>
        <th align="center">
            <form action="/lab2_war_exploded/HFDeleteAccount?type=civil_servant&id=<%=employees[i].getId()%>" method="post">
                <input type="submit" value="Delete">
            </form>
        </th>
    </tr>
    <% } %>
</table>

</body>
</html>
