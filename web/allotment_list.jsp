<%--
  Created by IntelliJ IDEA.
  User: deepaksood619
  Date: 3/6/16
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="in.deepaksood.Allotment" %>
<%@ page import="in.objectmodels.FinalAllotment" %>


<% Class.forName("com.mysql.jdbc.Driver"); %>
<html>
<head>
    <title>Allotment List</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Allotment List</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>


<form action="index.jsp" method="post">
    <h1>Allotment List</h1>

<%
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD_Assignment_3","root","root");
    String getQuery = "SELECT * FROM FinalAllotment";
    PreparedStatement preparedStatement = connection.prepareStatement(getQuery);

    ResultSet resultSet = preparedStatement.executeQuery();
    %>
    <TABLE BORDER="1">
    <TR>
        <TH>Student Name</TH>
        <TH>Subject</TH>
    </TR>
    <%
    while(resultSet.next()) { %>

    <TR>
        <TD> <%= resultSet.getString(1) %></TD>
        <TD> <%= resultSet.getString(2) %></TD>
    </TR>

    <%
    }
%>
    </TABLE>

    <br>

    <button type="submit">Back</button>
</form>

</body>
</body>
</html>
