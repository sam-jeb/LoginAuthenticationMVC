<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h1><%= "Login" %>
</h1>
<table>
<form method="post"  action="/RequestContoller">
    <tr>
        <td><label for="email">Email:</label></td>
        <td><input type="email" id="email" name="email"></td>
    </tr>
    <tr>
        <td><label for="pw">Password:</label></td>
        <td><input type="password" id="pw" name="pw"></td>
    </tr>
    <tr>
        <td><button type="submit" >Submit</button></td>
    </tr>
    <input type="hidden" name="source" value="login">
</form>
</table>
<form  action="/RequestContoller">
    <button type="submit" >Signup</button>
</form>
${error}
</body>
</html>