<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Statistic Questions Page</title>
</head>
<body>
	<h1>Statistic Questions</h1>
	<br>

	<h3>What is your gender?</h3>
	<form method="get">
		<input type="checkbox" name="gender" value="female">female
		<input type="checkbox" name="gender" value="male">male
	</form>
	
	<h3>What is your age?</h3>
	<form action="TakeAge" method="get">
		 <input type="text" name="age"> <br>
	</form>

	<h3>What is your experience level?</h3>
	<form method="get">
		<input type="checkbox" name="expLvl" value="low">Low 
		<input type="checkbox" name="expLvl" value="medium">Medium 
		<input type="checkbox" name="expLvl" value="high">High
	</form>


	<form action="GreetingsPage.jsp">
		<input type="submit" value="Submit">
	</form>

	<form action="MarketingQuestionsPage.jsp">
		<input type="submit" value="Previous">
	</form>

	<form action="ProductPage.jsp">
		<input type="submit" value="Cancel">
	</form>

</body>
</html>