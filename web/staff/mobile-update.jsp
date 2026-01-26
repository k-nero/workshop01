<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Update Mobile</title>
        <%@include file="../common/header.jsp" %>
    </head>
    <body>
        <jsp:include page="header_staff.jsp" />

        <div class="container mt-4">
            <h3>Update Mobile</h3>
            
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>

            <c:if test="${mobile != null}">
                <form method="post" action="update-mobile" class="mt-3">
                    <input type="hidden" name="mobileId" value="${mobile.mobileId}">
                    
                    <div class="mb-3">
                        <label class="form-label">Mobile ID</label>
                        <input type="text" class="form-control" value="${mobile.mobileId}" disabled>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Mobile Name</label>
                        <input type="text" class="form-control" value="${mobile.mobileName}" disabled>
                    </div>
                    
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description" 
                                  maxlength="250" rows="3">${mobile.description}</textarea>
                    </div>
                    
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="number" class="form-control" id="price" name="price" 
                               step="0.01" min="0" value="${mobile.price}" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Year of Production</label>
                        <input type="number" class="form-control" value="${mobile.yearOfProduction}" disabled>
                    </div>
                    
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Quantity</label>
                        <input type="number" class="form-control" id="quantity" name="quantity" 
                               min="0" value="${mobile.quantity}" required>
                    </div>
                    
                    <div class="mb-3">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="notSale" name="notSale" value="true"
                                   ${mobile.notSale ? 'checked' : ''}>
                            <label class="form-check-label" for="notSale">
                                Not for Sale
                            </label>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Update</button>
                    <a href="mobile-list" class="btn btn-secondary">Cancel</a>
                </form>
            </c:if>
        </div>
    </body>
</html>
