<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
	<jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
	<jsp:body>
                <h3>Create new question for the product of the day</h3>
                <form method="POST" enctype="multipart/form-data">
                 <fieldset>
                 
                    <div class="row">
                        <div class="col-sm-12 col-md-3 label-parent">
                            <label for="question">Question</label>
                        </div>
                        <div class="col-sm-12 col-md-9 input-parent">
                            <input type="text" id="question"
							name="question">
                        </div>
                    </div>    
                         
                     <div class="row">
                        <div class="col-sm-12 col-md-3">
                           <input type="submit"
							value="Create new question">
                        </div>

                    </div>

                    </fieldset>
                </form>
    </jsp:body>
</t:pageLayout>