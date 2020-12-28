<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
    <jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
    <jsp:body>
    	 <div class="row">
             <div class="col-sm-12 col-md-3" style="text-align: center;">
             <c:choose>
                <c:when test="${success}">
             	<span style="font-size:8em; color: green; width:100%">
				  <i class="fas fa-check-circle"></i>
				</span>
				</c:when>
             	<c:otherwise>
             	<span style="font-size:8em; color: red; width:100%">
				  <i class="fas fa-exclamation-triangle"></i>
				</span>
				</c:otherwise>
			</c:choose>
             </div>
			 <div class="col-sm-12 col-md-9">
                 <h1>${message}</h1>
                              <c:choose>
                <c:when test="${back_link != null}">
             	<a href="${back_link}"><i class="fas fa-chevron-left"></i> Go back</a>
				</c:when>
			</c:choose>
             </div>
         </div>

    </jsp:body>
</t:pageLayout>