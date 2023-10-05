<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h1><%= "Login" %>
</h1>
<form method="post"  action="/RequestContoller">

    <label for="email">Email:</label>
    <input type="email" id="email" name="email"><br>

    <label for="pw">Password:</label>
    <input type="password" id="pw" name="pw"><br>

    <input type="hidden" name="source" value="login">
    <button type="submit" >Submit</button>

</form>
<form  action="/RequestContoller">
    <button type="submit" >Signup</button>
</body>
</html>