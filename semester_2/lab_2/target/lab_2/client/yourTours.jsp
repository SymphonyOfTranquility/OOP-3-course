<%@ page import="TableElements.Tour" %><%--
  Created by IntelliJ IDEA.
  User: art
  Date: 6/14/20
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("Tours") == null) {
        response.sendRedirect("/lab_2_war_exploded/yourTours");
        return;
    }
    Tour[] tours = (Tour[]) request.getAttribute("Tours");
    for (int i = 0;i < tours.length; ++i)
        System.out.println(tours[i].toString());
%>
<html>
<head>
    <title>Your tours</title>
</head>
<body>
<div>
    <h1>Available tours</h1>
    <br />
</div>
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr align="center">
        <th>id</th>
        <th>name</th>
        <th>type</th>
        <th>closed</th>
    </tr>
    <% for (int i = 0;i < tours.length; ++i) { %>
    <tr align="center">
        <%=tours[i].toTable()%>
    </tr>
    <% } %>
</table>


<div style="padding-left: 5px; padding-top: 5px">
    <br/>
    <a href="http://localhost:8080/lab_2_war_exploded/clientPage">Return to main</a>
</div>
</body>
</html>
