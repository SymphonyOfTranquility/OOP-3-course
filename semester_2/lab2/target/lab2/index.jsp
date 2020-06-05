<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Type of accounts</h2>


    <div style="padding-left: 5px; padding-top: 5px">
        <div>
            <a href="http://localhost:8080/lab2_war_exploded/checkPasswordHeadOfStuff"> Head of stuff </a>
        </div>
        <div>
            <a href="http://localhost:8080/lab2_war_exploded/checkPasswordClerk"> Clerk of court </a>
        </div>
        <div>
            <a href="http://localhost:8080/lab2_war_exploded/checkPasswordJudge"> Judge </a>
        </div>
        <div>
            <a href="http://localhost:8080/lab2_war_exploded/checkPasswordJudgeAssistant"> Judge assistant </a>
        </div>
    </div>
<h3>Statistics</h3>
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr>
        <th></th>
        <th>Obolon region</th>
        <th>Starokostyantinivskiy region</th>
        <th>Starosinyavskiy region</th>
        <th>Krasiliv region</th>
    </tr>
    <tr>
        <th>Average number of cases during the day</th>
        <th>100</th>
        <th>34</th>
        <th>5</th>
        <th>10</th>
    </tr>
    <tr>
        <th>The number of judges by the norm</th>
        <th>25</th>
        <th>8</th>
        <th>1</th>
        <th>3</th>
    </tr>
    <tr>
        <th>Real number of judges</th>
        <th>17</th><th>5</th><th>1</th><th>2</th>
    </tr>
    <tr>
        <th>Overload (1 < - hard) (< 1 - easy)</th>
        <th>1.4</th><th>1.6</th><th>1</th><th>1.5</th>
    </tr>
</table>

<br />
<table border="3px solid black" cellpadding="5" cellspacing="0">
    <tr>
        <th>Case type</th>
        <th>On debt collection</th>
        <th>Road accident</th>
        <th>On debt for the loan</th>
        <th>Determination of the right to inheritance</th>
        <th>Aliments</th>
        <th>Theft</th>
        <th>Murder</th>
    </tr>
    <tr>
        <th>Complexity of case</th>
        <th>0.2</th>
        <th>0.4</th>
        <th>0.7</th>
        <th>1</th>
        <th>1.5</th>
        <th>1.8</th>
        <th>2.1</th>
    </tr>
    <tr>
        <th>Average number of cases<br /> during the year<br />for 1 judge</th>
        <th>252</th>
        <th>220</th>
        <th>126</th>
        <th>84</th>
        <th>59</th>
        <th>48</th>
        <th>42</th>
    </tr>
</table>
</body>
</html>
