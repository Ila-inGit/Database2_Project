<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="it.polimi.db2.utils.ImageUtils" %>
<%@ page import="it.polimi.db2.utils.FormatUtils" %>

<div class="col-sm-12  col-lg-4">
    <div class="card fluid">
        <h3>${product.getName()}<small>${FormatUtils.dateToString(product.getDisplayDate())}</small></h3>
        <c:choose>
        	<c:when test="${product.getPhoto() != null}">
        		<img class="section media" src="data:image/${ImageUtils.getImageExtension(product.getPhoto())};base64,${ImageUtils.toBase64(product.getPhoto())}"/>
        	</c:when>
        </c:choose>

        <div class="row">
			<c:choose>
				<c:when test="${product.getQuestions().size() > 0}">			
					<div class="col-sm-12 flex-align-center">
						<a href="${pageContext.request.contextPath}/questions/results?id=${product.getId()}"
							 class="button large" style="width: 85%;">See Results</a>
					</div>							
				</c:when>
			</c:choose>
			
			<c:choose>
				<c:when
					test="${FormatUtils.isNextOrTodayDate(product.getDisplayDate())}">
					<div class="col-sm-12 flex-align-center">
						<a href="${pageContext.request.contextPath}/questions/new?id=${product.getId()}"
							class="button large" style="width: 85%;">Create Question</a>
					</div>
				</c:when>
			</c:choose>

			
			<c:choose>
					<c:when
						test="${!FormatUtils.isNextOrTodayDate(product.getDisplayDate())}">
							<c:choose>
								<c:when test="${product.getQuestions().size() > 0}">				
									<div class="col-sm-12 flex-align-center">
										<a href="${pageContext.request.contextPath}/questions/delete?id=${product.getId()}" class="button large ybtn" style="width: 85%;">Delete Questionnaire</a>
									</div>				
								</c:when>
							</c:choose>
							<div class="col-sm-12 flex-align-center">
				            	<a href="${pageContext.request.contextPath}/product/delete?id=${product.getId()}" class="button secondary large" style="width: 85%;">Delete Product</a>
				            </div>
					</c:when>
				</c:choose>

        </div>
    </div>
</div>