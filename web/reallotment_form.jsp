<%--
  Created by IntelliJ IDEA.
  User: deepaksood619
  Date: 3/6/16
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reallotment Form</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reallotment Form</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>

<form action="reallotment" method="post">

    <%
        String studentName = (String) request.getAttribute("student_name");
    %>

    <h1>Welcome <%out.println(studentName);%></h1>

    <fieldset>
        <legend><span class="number">1</span>Select New Preference</legend>

        <label for="new_preference">New Preference</label>
        <select id="new_preference" name="new_preference">
            <optgroup label="Subjects">
                <option disabled selected value> -- select an option -- </option>
                <option value="graduate_algorithm">Graduate Algorithm</option>
                <option value="mobile_computing">Mobile Computing</option>
                <option value="security_engineering">Security Engineering</option>
                <option value="oopd">OOPD</option>
            </optgroup>
        </select>
    </fieldset>

    <%request.getSession().setAttribute("student_name",studentName);%>

    <br>
    <button type="submit">Reallot</button>
</form>

<%--<form action="ShowResults" method="post">
    <button type="submit">Check Allotment Results</button>
</form>--%>

</body>
</body>
</html>
