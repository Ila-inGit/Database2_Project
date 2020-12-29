<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="it.polimi.db2.utils.ImageUtils" %>

<div class="col-sm-12  col-lg-4">
    <div class="card fluid">
        <h3>${product.getName()}<small>${product.getDisplayDate()}</small></h3>
        <c:choose>
        	<c:when test="${product.getPhoto() != null}">
        		<img class="section media" src="data:image/${ImageUtils.getImageExtension(product.getPhoto())};base64,${ImageUtils.toBase64(product.getPhoto())}"/>
        	</c:when>
        </c:choose>

        <div class="row">
            <div class="col-sm-12 flex-align-center">
           		 <c:choose>
           		 <c:when test="${product.getQuestions().size() > 0}">
            		<a href="#" class="button large" style="width: 85%;">See questionnaire</a>
            	</c:when>
            	<c:otherwise>
            		<a href="#" class="button large" style="width: 85%;">Create questionnaire</a>
            	</c:otherwise>
            	</c:choose>
            </div>
            <div class="col-sm-12 flex-align-center">
            	<a href="${pageContext.request.contextPath}/product/delete?id=${product.getId()}" class="button secondary large" style="width: 85%;">Delete</a>
            </div>
        </div>
    </div>
</div>