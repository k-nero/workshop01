<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Create Mobile</title>
        <%@include file="../common/header.jsp" %>
    </head>
    <body>
        <jsp:include page="header_staff.jsp" />

        <div class="container mt-4">
            <h3>Create New Mobile</h3>
            
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>

            <form method="post" action="create-mobile" class="mt-3">
                <div class="mb-3">
                    <label for="mobileId" class="form-label">Mobile ID <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="mobileId" name="mobileId" 
                           maxlength="10" required>
                </div>
                
                <div class="mb-3">
                    <label for="mobileName" class="form-label">Mobile Name <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="mobileName" name="mobileName" 
                           maxlength="20" required>
                </div>
                
                <div class="mb-3">
                    <label for="description" class="form-label">Description <span class="text-danger">*</span></label>
                    <textarea class="form-control" id="description" name="description" 
                              maxlength="250" rows="3" required></textarea>
                </div>
                
                <div class="mb-3">
                    <label for="price" class="form-label">Price <span class="text-danger">*</span></label>
                    <input type="number" class="form-control" id="price" name="price" 
                           step="0.01" min="0" required>
                </div>
                
                <div class="mb-3">
                    <label for="yearOfProduction" class="form-label">Year of Production</label>
                    <input type="number" class="form-control" id="yearOfProduction" name="yearOfProduction" 
                           min="1900" max="2100">
                </div>
                
                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" 
                           min="0" value="0">
                </div>
                
                <div class="mb-3">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="notSale" name="notSale" value="true">
                        <label class="form-check-label" for="notSale">
                            Not for Sale
                        </label>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Create</button>
                <a href="mobile-list" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </body>
</html>
