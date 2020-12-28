<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
    <jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
    <jsp:body>
    	 <div class="row">
             <div class="col-sm-1">
                 <span class="icon-alert ico"></span>
             </div>
			 <div class="col-sm-11">
                 <h1>${message}</h1>
             </div>
         </div>

    </jsp:body>
</t:pageLayout>