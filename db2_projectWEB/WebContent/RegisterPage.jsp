<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
<style>.error { color: red; } .success { color: green; }</style>
</head>
<body>
	<h1>Register Page</h1>
	
	<form action="RegisterUser" method="POST">
		Email: <input type="text" name="email"> <br>
		UserName: <input type="text" name="username"> <br>
		Password: <input type="password" name="pwd"><br>
		<span class="error">${message}</span> <br>
		<input type="submit" value="Register">
		<span class="success">${success}</span> <br>
	</form>
	
	<br>

	<fieldset>
		<form action="LoginPage.jsp">
			<input type="submit" value = "Go to Log In Page">
		</form>
	</fieldset>
</body>
</html>