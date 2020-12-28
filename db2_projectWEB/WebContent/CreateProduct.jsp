<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageLayout>
    <jsp:attribute name="header">
      <jsp:include page="components/topBar.jsp" />
    </jsp:attribute>
    <jsp:body>
                <h3>Create new product of the day</h3>
                <form method="POST" enctype="multipart/form-data">
                 <fieldset>
                 
                    <div class="row">
                        <div class="col-sm-12 col-md-3 label-parent">
                            <label for="fname">Product Name *</label>
                        </div>
                        <div class="col-sm-12 col-md-9 input-parent">
                            <input type="text" id="fname" name="fname">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12 col-md-3 label-parent">
                            <label for="fdate">Display Date</label>
                        </div>
                        <div class="col-sm-12 col-md-9 input-parent">
                            <input type="date" id="fdate" name="fdate">
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-sm-12 col-md-3 label-parent">
                            <label for="fimage">Product Image</label>
                        </div>
                        <div class="col-sm-12 col-md-9 input-parent">
                            <input type="file" id="fimage" name="fimage">
                        </div>
                    </div>

                    
                     <div class="row">
                        <div class="col-sm-12 col-md-3">
                           <input type="submit" value="Create new product">
                        </div>

                    </div>
                    

                    </fieldset>
                </form>
    </jsp:body>
</t:pageLayout>