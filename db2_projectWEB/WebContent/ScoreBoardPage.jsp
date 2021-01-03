<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:scoreBoardLayout>

	<jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
    
<jsp:body>
	<table class="striped">
	  <caption>ScoreBoard</caption>
	  <thead>
	    <tr>
	      <th>UserName</th>
	      <th>Points</th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:choose>
			<c:when test="${scoreBoard != null}">
					
				<c:forEach items="${scoreBoard}" var="entry">
					<tr>
						<td data-label="UserName">${entry.key}</td>
				      	<td data-label="Points">${entry.value}</td>
					</tr>
				</c:forEach>
			
			</c:when>
			
			<c:otherwise>
				<tr>
					<td data-label="UserName">Nothing...this is so sad</td>
				</tr>
			</c:otherwise>	
					
		</c:choose>
	  </tbody>
	</table>
</jsp:body>
</t:scoreBoardLayout>