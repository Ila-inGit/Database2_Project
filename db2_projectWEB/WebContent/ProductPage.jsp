<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
    <jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
    <jsp:body>
         <div class="row">
             <div class="col-sm-10">
                 <h1>${product.getName()}</h1>
             </div>
             	<c:choose>
				    <c:when test="${usr.isAdmin() == true}">
				    	 <div class="col-sm-2">
			                 <a href="#" role="button">Results</a>
			                 <a href="#" role="button">Delete</a>
			             </div>
				    </c:when>
				</c:choose>

             <div class="col-sm-12 img-container">
             	<c:choose>
				    <c:when test="${product_image != null}">
				    	<img alt="Product image" class ="product-img" src="data:image/${product_img_ext};base64,${product_image}"/>
				    </c:when>
				    <c:otherwise>
				       	 No image
				    </c:otherwise>
				</c:choose>
             </div>

             <div class="col-sm-12">
                 <h1>Reviews</h1>
                 <hr>
                 <div class="row">
                 	<c:forEach items="${product.getReviews()}" var ="review">
	                 	 <div class="col-sm-12 col-md-6">
	                         <blockquote cite="${review.getUser().getUserName()}">${review.getBody()}</blockquote>
	                     </div>
			       </c:forEach> 
                 </div>
             </div>
         </div>
    </jsp:body>
</t:pageLayout>