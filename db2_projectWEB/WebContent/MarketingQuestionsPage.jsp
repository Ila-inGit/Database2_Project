<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%! int numberQ; %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Marketing Questions</title>
</head>
<body>
	<h1>Marketing Questions</h1>
	<br>

	<form method="get" enctype="multipart/form-data">
		<fieldset>
			<c:forEach items="${marketingQuestions}" var="question">
				<%
					numberQ++;
				%>
			Question <%=numberQ%>
				<br>
				<c:out value="${question.body}" />
				<br>
				<input type="text" id="answer" name="answer">
				<br>
			</c:forEach>
		</fieldset>
	</form>


	<form action="StatisticQuestionsPage.jsp">
		<input type="submit" value="Next">
	</form>
	<%-- <a href="output.jsp">Check Output Page Here </a> --%>
</body>
</html>