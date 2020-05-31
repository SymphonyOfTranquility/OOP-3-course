<%--
  Created by IntelliJ IDEA.
  User: art
  Date: 5/29/20
  Time: 11:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Завести нову справу</title>
</head>
<body>
<form action="clerkCreateNewCase" method="post">

    <div class="container">
        <table>
            <tr>
                <th><label for="year_id"><b>Type id</b></label></th>
                <th><input type="text" placeholder="Enter year id" name="year_id" required></th>
            </tr>
            <tr>
                <th><label for="plaintiff"><b>Plaintiff</b></label></th>
                <th><input type="text" placeholder="Enter plaintiff" name="plaintiff" required></th>
            </tr>
            <tr>
                <th><label for="defendant"><b>Defendant</b></label></th>
                <th><input type="text" placeholder="Enter defendant" name="defendant" required></th>
            </tr>
            <tr>
                <th><label for="text_of_case"><b>Theme of case</b></label></th>
                <th><input type="text" placeholder="Enter text of case" name="text_of_case" required></th>
            </tr>
            <tr>
                <th><button type="submit">Send</button></th>
            </tr>
            <tr>
                <th><a href="http://localhost:8080/lab2_war_exploded/">Return to main</a></th>
            </tr>
        </table>
    </div>

</form>
</body>
</html>
