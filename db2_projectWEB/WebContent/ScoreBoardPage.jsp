<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ScoreBoard Page</title>
<style>.error { color: red; } .success { color: green; }</style>
</head>
<body>
	<h1>Charles Leclerc's points Page</h1>
	
	<form action="ScoreBoard" method="POST">
		<span class="error">${message}</span> <br>
		<input type="submit" value="Get scoreBoard">
		<span class="success">${success}</span> <br>
	</form>
	
	<br>
		<fieldset>
			<h2>this is so sad</h2>
			<c:forEach items="${scoreBoard}" var="entry">
			     UserName = ${entry.key}, Scores = ${entry.value}<br>
			</c:forEach>
		</fieldset>
	<br>

	<fieldset>
		<form action="LoginPage.jsp">
			<input type="submit" value = "Go to Log In Page">
		</form>
	</fieldset>
	<fieldset>
		<form action="RegisterPage.jsp">
			<input type="submit" value = "Go to Register Page">
		</form>
	</fieldset>
</body>
</html>