<%--
  Created by IntelliJ IDEA.
  User: sjebastin
  Date: 10/4/2023
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h1>Sign-Up </h1>
<form method="post"  action="/RequestContoller">

  <label for="fname">First Name: </label>
  <input type="text" id="fname" name="fname"><br>

  <label for="lname">Last Name: </label>
  <input type="text" id="lname" name="lname"><br>

  <label for="email">Email: </label>
  <input type="email" id="email" name="email"><br>

  <label for="pw">Password: </label>
  <input type="password" id="pw" name="pw"><br>

  <label for="rpw">Re-type Password:</label>
  <input type="password" id="rpw" name="rpw"><br>

  <input type="hidden" name="source" value="signup">
  <button type="submit" >Submit</button>

</form>
</body>
</html>
