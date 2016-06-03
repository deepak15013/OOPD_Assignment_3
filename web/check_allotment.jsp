<%--
  Created by IntelliJ IDEA.
  User: deepaksood619
  Date: 3/6/16
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check Allotment</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Check Allotment</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>

<form action="search" method="get">

    <h1>Check Allotment</h1>

    <fieldset>
        <legend><span class="number">1</span>Search by roll number</legend>
        <label for="search_roll">Roll Number:</label>
        <input type="text" id="search_roll" name="search_roll">
    </fieldset>
    <br>
    <button type="submit">Search</button>
</form>

<br>

<form action="allotment_list.jsp" method="post">
    <button type="submit">Show Full Allotment List</button>
</form>

</body>
</body>
</html>
