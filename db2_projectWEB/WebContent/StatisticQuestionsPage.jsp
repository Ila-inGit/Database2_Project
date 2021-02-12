<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
	<jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
	<jsp:body>
	<div class="row">
        <div class="col-sm-12">
			<h1>Statistic Questions</h1>
		</div>
	</div>
	<form method="GET" enctype="multipart/form-data">
		<div class="row">
        	<div class="col-sm-12">
				<h3>What is your gender?</h3>
			</div>
		</div>
		<div class="row">
        	<div class="col-sm-12">
				<input type="radio" id="gender" name="gender" value="female">female
				<input type="radio" id="gender" name="gender" value="male">male
			</div>
		</div>
	
		<div class="row">
        	<div class="col-sm-12 col-md-3">
              	<h3>What is your age?</h3>
         	</div>
        	<div class="col-sm-12 col-md-9">
              	<input type="number" id="age" name="age">
         	</div>
    	</div> 

		<div class="row">
        	<div class="col-sm-12">
				<h3>What is your experience level?</h3>
			</div>
		</div>
		<div class="row">
        	<div class="col-sm-12">
				<input type="radio" id="expLvl" name="expLvl" value="low">Low 
				<input type="radio" id="expLvl" name="expLvl" value="medium">Medium 
				<input type="radio" id="expLvl" name="expLvl" value="high">High
			</div>
		</div>
		
		<input type="submit" name="Submit" value="Submit">
	</form>
	
	<a href="${pageContext.request.contextPath}/questions"
                      class="button small" style="width: 10%;">Previous</a>
        
    <a href="${pageContext.request.contextPath}/product"
                      class="button small" style="width: 10%;">Cancel</a>
                      
	<%-- <form action="ResultPage.jsp">
		<button type="submit" name="submit" value="submit">Submit</button>
		<button type="submit" name="previous" value="previous">Previous</button>
		<button type="submit" name="cancel" value="cancel">Cancel</button>
	</form>
			
	<form action="MarketingQuestionsPage.jsp">
		
	</form>

	<form action="ProductPage.jsp">
		
	</form>  --%>
	
	
   </jsp:body>
</t:pageLayout>