<%@ page import="tableElements.Court" %><%--
  Created by IntelliJ IDEA.
  User: art
  Date: 5/31/20
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("Courts") == null) {
        response.sendRedirect("/lab2_war_exploded/HFCreateNewAccount");
        return;
    }
    Court[] courts = (Court[]) request.getAttribute("Courts");
    for (int i = 0;i < courts.length; ++i)
        System.out.println(courts[i].toString());
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Add new person</h2>
    <%
        String login_msg=(String)request.getAttribute("error");
        if(login_msg!=null)
            out.println("<font color=red size=4px>"+login_msg+"</font>");
    %>
    <form action="HFCreateNewAccount" method="post">
        <table>
            <tr>
                <th><label for="name"><b>Name</b></label></th>
                <th><label for="surname">Surname</label></th>
                <th><label for="profession">Profession</label></th>
                <th><label for="court">Choose court</label></th>
            </tr>
            <tr>
                <th>
                    <input type="text" name="name">
                </th>
                <th>
                    <input type="text" name="surname">
                </th>
                <th>
                    <select name="profession">
                        <option value="judge">judge</option>
                        <option value="judge_assistant">judge assistant</option>
                        <option value="clerk">court clerk</option>
                        <option value="secretary">secretary</option>
                        <option value="guardian">guardian</option>
                        <option value="head_of_stuff">head of stuff</option>
                    </select>
                </th>
                <th>
                    <select name="court">
                        <% for (int i = 0;i < courts.length; ++i) {%>
                            <option value="<%=courts[i].getId()%>"><%=courts[i].getCityName()%></option>
                        <% } %>
                    </select>
                </th>
            </tr>
        </table>
        <input type="submit" value="Submit">
    </form>

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
