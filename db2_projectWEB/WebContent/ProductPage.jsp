<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
    <jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
    <jsp:body>
		<c:choose>
		    <c:when test="${product != null}">
		    	 <div class="row">
		             <div class="col-sm-10">
		                 <h1>${product.getName()}</h1>
		             </div>
		             	<c:choose>
						    <c:when test="${usr.isAdmin() == true}">
						    
						    	<div class="col-sm-1 flex-vertical-center ">
					                <a href="${pageContext.request.contextPath}/product/new">
					                    <span class="tooltip" aria-label="Create new product"><i class="fas fa-plus-circle fa-2x"></i></span>
					                </a>
					            </div>
					            <div class="col-sm-1 flex-vertical-center">
					            	<c:choose>
					            		<c:when test="${product.getQuestions().size() > 0}">
							                <a href="${pageContext.request.contextPath}/questions/results?id=${product.getId()}">
							                    <span class="tooltip" aria-label="See poll results"><i class="fas fa-poll fa-2x"></i></span>
							                </a>
						                </c:when>
					                </c:choose>
					            </div>
					            
						    </c:when>
						    <c:otherwise>
						    		<c:choose>
					            		<c:when test="${product.getQuestions().size() > 0 && !poll_done}">
					            			<div class="col-sm-1 flex-vertical-center ">
								                <a href="${pageContext.request.contextPath}/questions">
								                    <span class="tooltip" aria-label="Answer questions"><i class="fas fa-poll fa-2x"></i></span>
								                </a>
							                </div>
						                </c:when>
					                </c:choose> 
						    
						    </c:otherwise>
						    
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
		                 <c:choose>
						    <c:when test="${product.getReviews().size() > 0}">
						    	<div class="row">
		                 			<c:forEach items="${product.getReviews()}" var ="review">
					                 	 <div class="col-sm-12 col-md-6">
					                         <blockquote cite="${review.getUser().getUserName()}">${review.getBody()}</blockquote>
					                     </div>
							       </c:forEach> 
				                 </div>
						    </c:when>
						    <c:otherwise>
						    	<div class="row">
						    		<div class="col-sm-12" style="text-align:center;">
						       	 		<h4 style="widht:100%;">There are no reviews at the moment</h4>
						       	 	</div>
						       	 </div>
						    </c:otherwise>
						</c:choose>

		             </div>
		         </div>
		    </c:when>
		    <c:otherwise>
		    	<div class="row">
		    		 <c:choose>
						    <c:when test="${usr.isAdmin() == true}">
						    	<div class="col-sm-11"></div>
						    	<div class="col-sm-1 flex-vertical-center" style="margin-top:5px;'">
					                <a href="${pageContext.request.contextPath}/product/new">
					                    <span class="tooltip" aria-label="Create new product"><i class="fas fa-plus-circle fa-2x"></i></span>
					                </a>
					            </div>			            
						    </c:when>						    
						</c:choose>
		    		<div class="col-sm-12 img-container">
							<img alt="Product image" class ="product-img" src="${pageContext.request.contextPath}/img/no_prod.png"/>
		             </div>
		             
		             <div class="col-sm-12">
		                 <h3>A raccoon ate today's product of the day so we have nothing to show you.</h3>
		             </div>			    
		
		         </div>

		    </c:otherwise>
		</c:choose>

    </jsp:body>
</t:pageLayout>