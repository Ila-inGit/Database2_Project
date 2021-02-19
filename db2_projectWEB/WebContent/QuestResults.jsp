<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="it.polimi.db2.utils.FormatUtils" %>

<t:pageLayout>

	<jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
	
<jsp:body>
	
	<table class="striped">
	  <caption>Users</caption>
	  <thead>
	    <tr>
	      <th>Username</th>
	      <th>Have they submitted the questionnaire?</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td data-label="UserName">${user.key.getUserName()}</td>
				<td data-label="Points">${user.value}</td>
			</tr>
		</c:forEach>		
	  </tbody>
	</table>

     <h3>Questionnaire Results</h3>
                <form method="GET" enctype="multipart/form-data">
                		
						<c:forEach items="${questions}" var="question">
                   			<div class="row">
                    		    <div class="col-sm-12 col-md-3 label-parent">
                        	    	<label for="questions">Question: ${question.getBody()}</label>
                        		</div>
                        	</div>
                        		<br>
                        		
							<table class="striped">
							  <caption>Answers submitted</caption>
							  <thead>
							    <tr>
							      <th>Username</th>
							      <th>Answer</th>
							    </tr>
							  </thead>
							  <tbody>
								<c:forEach items="${questAnswers.get(question.getId())}" var="answer">
									<tr>
										<td data-label="UserName">${answer.getUser().getUserName()}</td>
										<td data-label="UserName">${answer.getBody()}</td>
									</tr>
								</c:forEach>		
							  </tbody>
							</table>
                   		</c:forEach> 
                </form> 
                
      <table class="striped">
	  <caption>Statistical Results</caption>
	  <thead>
	    <tr>
	      <th>Username</th>
	      <th>Gender</th>
	      <th>Age</th>
	      <th>Experience Level</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${product.getStatAnswers()}" var="ans">
			<c:choose>
				<c:when test="${ans.getGender() != null || ans.getAge() != null || ans.getExpLvl() != null }">			
					<tr>
						<td >${ans.getUser().getUserName()}</td>
						<td >${FormatUtils.replaceNullable(ans.getGender())}</td>
						<td >${FormatUtils.replaceNullable(ans.getAge())}</td>
						<td >${FormatUtils.replaceNullable(ans.getExpLvl())}</td>
					</tr>
				</c:when>
			</c:choose>

		</c:forEach>		
	  </tbody>
	</table>         
                          
    </jsp:body>
</t:pageLayout>