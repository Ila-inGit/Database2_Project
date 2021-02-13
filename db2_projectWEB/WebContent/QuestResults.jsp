<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:pageLayout>

	<jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
	
<jsp:body>
	
	<table class="striped">
	  <caption>User submitting questionnaire</caption>
	  <thead>
	    <tr>
	      <th>UserName</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td data-label="UserName">${user}</td>
			</tr>
		</c:forEach>		
	  </tbody>
	</table>
	
	<table class="striped">
	  <caption>User who have deleted their questionnaire</caption>
	  <thead>
	    <tr>
	      <th>UserName</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${deletedUsers}" var="deletedUser">
			<tr>
				<td data-label="UserName">${deletedUser}</td>
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
							      <th>UserName</th>
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
                
                          
    </jsp:body>
</t:pageLayout>