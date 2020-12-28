<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="#" class="logo">Hi, ${usr.getUserName()}!</a>

<div class="right">
	<a href="#" class="button">Home</a>
	<c:choose>
	    <c:when test="${usr.isAdmin() == true}">
	    	 <a href="#" class="button">Admin</a>
	    </c:when>
	</c:choose>

    <a href="${pageContext.request.contextPath}/LogOut" class="button">Logout</a>
</div>