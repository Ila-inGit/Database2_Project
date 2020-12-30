<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="${pageContext.request.contextPath}" class="logo">Hi, ${usr.getUserName()}!</a>

<div class="right">
	<c:choose>
	    <c:when test="${usr.isAdmin() == true}">
	    	 <a href="${pageContext.request.contextPath}/dashboard" class="button">Dashboard</a>
	    </c:when>
	</c:choose>
	<a href="${pageContext.request.contextPath}/scoreboard" class="button">Scoreboard</a>


    <a href="${pageContext.request.contextPath}/logout" class="button">Logout</a>
</div>