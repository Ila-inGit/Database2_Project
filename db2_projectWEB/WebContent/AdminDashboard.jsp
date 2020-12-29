<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
    <jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
    <jsp:body>
          <div class="row">
          	
              <div class="col-sm-10"><h1>Products</h1></div>
              <div class="col-sm-1 flex-vertical-center ">
                  <a href="${pageContext.request.contextPath}/product/new">
                      <span class="tooltip" aria-label="Create new product"><i class="fas fa-plus-circle fa-2x"></i></span>
                  </a>
              </div>
              <div class="col-sm-1 flex-vertical-center ">
                  <a href="#">
                      <span class="tooltip" aria-label="See older products"><i class="fas fa-history fa-2x"></i></span>
                  </a>
              </div>

              <div class="col-sm-12">
                  <div class="row">
					<c:choose>
						<c:when test="${products == null || products.size() <= 0}">
							<h3>No products</h3>
						</c:when>
						<c:otherwise>
							<c:forEach items="${products}" var ="prod">
									<c:set var="product" value="${prod}" scope="request"/>
				                 	 <jsp:include page="components/productCard.jsp" />
						       </c:forEach> 
						</c:otherwise>
					</c:choose>                      
                  </div>
              </div>
          </div>

    </jsp:body>
</t:pageLayout>