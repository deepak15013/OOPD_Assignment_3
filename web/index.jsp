<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TA Management Portal</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/main.css">
  </head>
  <body>

  <form action="registration" method="post">

    <h1>Student Registration</h1>

    <fieldset>
      <legend><span class="number">1</span>Your basic info</legend>
      <label for="name">Name:</label>
      <input type="text" id="name" name="name">

      <label for="rollNumber">Roll Number:</label>
      <input type="text" id="rollNumber" name="rollNumber">

      <label for="cgpa">CGPA:</label>
      <input type="number" step="0.01" id="cgpa" name="cgpa">

      <label>Program:</label>
      <input type="radio" id="UG" value="UG" name="program"><label for="UG" class="light">UG</label><br>
      <input type="radio" id="PG" value="PG" name="program"><label for="PG" class="light">PG</label><br>
      <input type="radio" id="PHD" value="PHD" name="program"><label for="PHD" class="light">PHD</label>
    </fieldset>

    <br>

    <fieldset>
      <legend><span class="number">2</span>Preferences</legend>
      <label for="first_preference">First Preference</label>
      <select id="first_preference" name="first_preference">
        <optgroup label="Subjects">
          <option disabled selected value> -- select an option -- </option>
          <option value="graduate_algorithm">Graduate Algorithm</option>
          <option value="mobile_computing">Mobile Computing</option>
          <option value="security_engineering">Security Engineering</option>
          <option value="oopd">OOPD</option>
        </optgroup>
      </select>

    </fieldset>
    <br>
    <button type="submit">Register</button>
  </form>

  <form action="ShowResults" method="post">
    <button type="submit">Check Allotment Results</button>
  </form>

  <form action="show_all_students.jsp" method="post">
    <button type="submit">Show All Students</button>
  </form>

  </body>
  </body>
</html>
