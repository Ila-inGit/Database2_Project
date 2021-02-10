<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:pageLayout>
	<jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
	<jsp:body>
                <h3>Marketing questions</h3>
                <form method="GET" enctype="multipart/form-data" action="StatisticQuestionsPage.jsp">
						<c:forEach items="${marketingQuestions}" var="question">
                   			<div class="row">
                    		    <div class="col-sm-12">
                        	    	<label for="marketingQuestions">${question.getBody()}</label>
                        		</div>
                        		<div class="col-sm-12">
                           		<textarea id="answers" name="answers" rows="7" cols="124" required ></textarea>
                       			</div>
                   			</div>
                   		</c:forEach> 
                   		
                   		<div class="row">
                    		<div class="col-sm-12 col-md-2">
                           		<input type="submit" value="Next">
                    		</div>
                		</div> 		
                </form>
    </jsp:body>
</t:pageLayout>