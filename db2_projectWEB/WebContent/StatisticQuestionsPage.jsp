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

	<div class="row">
        <div class="col-sm-12">
			<h3>What is your gender?</h3>
		</div>
	</div>
	<form method="get">
		<input type="radio" name="gender" value="female">female
		<input type="radio" name="gender" value="male">male
	</form>
	
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
	<form method="get">
		<input type="radio" name="expLvl" value="low">Low 
		<input type="radio" name="expLvl" value="medium">Medium 
		<input type="radio" name="expLvl" value="high">High
	</form>
	
	<button type="submit" value="Submit">Submit</button>
	<form action="ResultPage.jsp">
		<input type="submit" value="Submit">
	</form>
			
	<form action="MarketingQuestionsPage.jsp">
		<input type="submit" value="Previous">
	</form>

	<form action="ProductPage.jsp">
		<input type="submit" value="Cancel">
	</form>
   </jsp:body>
</t:pageLayout>