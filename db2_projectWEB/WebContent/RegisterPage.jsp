<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:loginLayout>
    <jsp:body>
       <form action="register" method="POST">
         <fieldset>
            <legend>Register</legend>
            <div class="row">
                <div class="col-sm-12 col-md-3 label-parent">
                    <label for="email">Email</label>
                </div>
                <div class="col-sm-12 col-md-9 input-parent">
                    <input type="text" id="email" name="email">
                </div>
            </div>
            
            <div class="row">
                <div class="col-sm-12 col-md-3 label-parent">
                    <label for="username">Username</label>
                </div>
                <div class="col-sm-12 col-md-9 input-parent">
                    <input type="text" id="username" name="username">
                </div>
            </div>
            
            <div class="row">
                <div class="col-sm-12 col-md-3 label-parent">
                    <label for="pwd">Password</label>
                </div>
                <div class="col-sm-12 col-md-9 input-parent">
                    <input type="password" id="pwd" name="pwd">
                </div>
            </div>

            <span class="error">${message}</span> <br>
            <span class="success">${success}</span> <br>

            <div class="row">
                <div class="col-sm-12 col-md-3">
                   <input type="submit" value="Register">
                </div>

            </div>

            </fieldset>
            <a href="${pageContext.request.contextPath}/login">Already have an account? Login here!</a>
        </form>
    </jsp:body>
</t:loginLayout>