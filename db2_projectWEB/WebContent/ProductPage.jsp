<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product of the day</title>
</head>
<body>
	<h3>${product.getName()}</h3>
	<c:choose>
	    <c:when test="${product_image != null}">
	    	<img alt="product image" src="data:image/jpg;base64,${product_image}"/>
	    </c:when>
	    <c:otherwise>
	       	 No image
	    </c:otherwise>
	</c:choose>
	<br>
	<span><a href="#">Fill Questionaire</a> - <a href="#">[ADMIN] Show Answers</a></span>
	<br>
	<h3>Reviews</h3>
	<table border="1" style="width:90%">
	   <c:forEach items="${product.getReviews()}" var ="review">
            <tr>  
            	<td>${review.getUser().getUserName()}</td>    
                <td>${review.getBody()}</td>
            </tr>
       </c:forEach> 
	</table>
	<br>
	<span>Admin: <a href="#">Create Product</a> - <a href="#">Delete Product</a></span>
</body>
</html>