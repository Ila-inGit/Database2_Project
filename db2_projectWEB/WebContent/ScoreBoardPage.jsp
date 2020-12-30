<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
    <jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
    <jsp:body>
		<h1>Scoreboard</h1>
		<br>
		<fieldset>
			<c:choose>
				<c:when test="${scoreBoard == null}">
					<c:forEach items="${scoreBoard}" var="entry">
					     UserName = ${entry.key}, Scores = ${entry.value}<br>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h3> Nothing...this is so sad</h3>
				</c:otherwise>			
			</c:choose>
		</fieldset>
    </jsp:body>
</t:pageLayout>