<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:scoreBoardLayout>
	<jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
	<jsp:body>
		<h1 style="widht:100%; text-align: center;">ScoreBoard</h1>
		<br>
		<fieldset>
			<c:choose>
				<c:when test="${scoreBoard != null}">
					<div class="row">
						<c:forEach items="${scoreBoard}" var="entry">
								UserName = ${entry.key} ==>> Scores = ${entry.value}<br>
						</c:forEach>
					</div>
				</c:when>
				
				<c:otherwise>
					<div class="row">
						<div class="col-sm-12" style="text-align: center;">
							<h3 style="widht: 100%;"> Nothing...this is so sad</h3>
						</div>
					</div>
				</c:otherwise>			
			</c:choose>
		</fieldset>
    </jsp:body>
</t:scoreBoardLayout>