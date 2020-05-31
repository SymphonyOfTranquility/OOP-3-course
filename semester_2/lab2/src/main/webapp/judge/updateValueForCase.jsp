<%@ page import="tableElements.Case" %><%--
  Created by IntelliJ IDEA.
  User: art
  Date: 5/31/20
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("Case") == null) {
        response.sendRedirect("/lab2_war_exploded/judgeMainPage");
        return;
    }
    Case curCase = (Case) request.getAttribute("Case");
    System.out.println(curCase.toString());
%>
<html>
<head>
    <title>Update values</title>
</head>

<body>
    <h1>Change case №<%=curCase.getId()%></h1>
    <table border="3px solid black" cellpadding="5" cellspacing="0">
        <tr align="center">
            <th>overall id</th> <th>type id</th> <th>plaintiff</th> <th>defendant</th>
            <th>theme of case</th> <th>is correct</th> <th>date of trial</th> <th>judgment</th>
            <th>closed</th>
        </tr>
        <tr align="center">
            <%=curCase.toTable()%>
        </tr>
    </table>
    <br/>
    <form action="judgeUpdateCase?id=<%=curCase.getId()%>" method="post">
        <table>
            <tr>
                <th><label for="is_correct"><b>correctness</b></label></th>
                <th><label for="date_of_trial">Date of trial</label></th>
                <th><label for="judgment">Judgment</label></th>
                <th><label for="closed"><b>closed</b></label></th>
            </tr>
            <tr>
                <th>
                    <select name="is_correct">
                        <option value="None">None</option>
                        <option value="Yes">Yes</option>
                        <option value="No">No</option>
                    </select>
                </th>
                <th>
                    <input type="date" name="date_of_trial">
                </th>
                <th>
                    <textarea name="judgment"></textarea>
                </th>
                <th>
                    <select name="closed">
                        <option value="No">No</option>
                        <option value="Yes">yes</option>
                    </select>
                </th>
            </tr>
        </table>
        <div style="padding-left: 5px; padding-top: 5px"></div>
        <input type="submit" value="Submit">

    </form>
    <div style="padding-left: 5px; padding-top: 5px">
        <br/>
        <a href="http://localhost:8080/lab2_war_exploded/">Return to main</a>
    </div>
    <div style="padding-left: 5px; padding-top: 5px">
        <br/>
        <a href="http://localhost:8080/lab2_war_exploded/judgeMainPage">Return to cases list</a>
    </div>
</body>
</html>
