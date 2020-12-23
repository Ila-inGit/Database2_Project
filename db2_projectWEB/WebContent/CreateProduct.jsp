<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Create product</h1>
	<form method="POST" enctype="multipart/form-data">
		  <label for="fname">Product Name:</label><br>
  		  <input type="text" id="fname" name="fname"><br>
  		  
  		  <label for="fimage">Product Image:</label><br>
  		  <input type="file" id="fimage" name="fimage"><br>
  		  
  		  <label for="fdate">Display Date:</label><br>
  		  <input type="date" id="fdate" name="fdate"><br>
  		  
  		  <input type="submit">
	</form>
</body>
</html>