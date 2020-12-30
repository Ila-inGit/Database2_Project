<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="${pageContext.request.contextPath}" class="logo">Hi, ${usr.getUserName()}!</a>

<div class="right">
	<c:choose>
	    <c:when test="${usr.isAdmin() == true}">
	    	 <a href="${pageContext.request.contextPath}/dashboard" class="button">Dashboard</a>
	    </c:when>
	</c:choose>
	<a href="${pageContext.request.contextPath}/leaderboard" class="button">Leaderboard</a>
	<a href="${pageContext.request.contextPath}/profile" class="button">Profile</a>


    <a href="${pageContext.request.contextPath}/LogOut" class="button">Logout</a>
</div>