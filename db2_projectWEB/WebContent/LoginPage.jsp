<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<style>.error { color: red; } .success { color: green; }</style>
</head>
<body>
	<h1>Login Page</h1>

	<form action="login" method="POST">
		UserName: <input type="text" name="username"> <br>
		Password: <input type="password" name="pwd"><br>
		<span class="error">${message}</span> <br>
		<input type="submit" value="Log In">
		<span class="success">${success}</span> <br>
	</form>

<br>

	<a href="${pageContext.request.contextPath}/register">Register</a>
</body>
</html>