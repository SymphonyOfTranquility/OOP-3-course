<%@ page import="TableElements.Tour" %><%--
  Created by IntelliJ IDEA.
  User: art
  Date: 6/14/20
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("Tours") == null) {
        response.sendRedirect("/lab_2_war_exploded/makeToursHot");
        return;
    }
    Tour[] tours = (Tour[]) request.getAttribute("Tours");
    for (int i = 0;i < tours.length; ++i)
        System.out.println(tours[i].toString());
%>
<html>
<head>
    <title>Hot Tours</title>
</head>
<body>

<div>
    <h1>All tours</h1>
    <br />
</div>
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr align="center">
        <th>id</th>
        <th>name</th>
        <th>type</th>
        <th>hot!</th>
    </tr>
    <% for (int i = 0;i < tours.length; ++i) { %>
    <tr align="center">
        <%=tours[i].toTable()%>
        <th align="center">
            <form action="/lab_2_war_exploded/makeToursHot?id=<%=tours[i].getId()%>&is_hot=<%=tours[i].isHot()%>" method="post">
                <input type="submit" value="Change hotness">
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
