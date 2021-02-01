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
                <form method="GET" enctype="multipart/form-data">
						<c:forEach items="${marketingQuestions}" var="question">
                   			<div class="row">
                    		    <div class="col-sm-12 col-md-3 label-parent">
                        	    	<label for="marketingQuestions">${question.getBody()}</label>
                        		</div>
                        		<div class="col-sm-12 col-md-9 input-parent">
                           		<textarea id="answers" name="answers" rows="10" cols="30" required>
                           		</textarea>
                       			</div>
                   			</div>
                   		</c:forEach> 
                </form>
                
                <div class="row">
                    <div class="col-sm-12 col-md-3">
                       	<form action="StatisticQuestionsPage.jsp">
                           <input type="submit" value="Next">
                        </form>
                    </div>
                </div>
    </jsp:body>
</t:pageLayout>