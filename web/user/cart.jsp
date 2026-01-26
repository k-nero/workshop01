<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Shopping Cart</title>
        <%@include file="../common/header.jsp" %>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Mobile Shop</a>
                <div class="navbar-nav ms-auto">
                    <span class="navbar-text me-3">Welcome, ${sessionScope.user.fullName}</span>
                    <c:if test="${sessionScope.role == 1 || sessionScope.role == 2}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/staff/mobile-list">Mobile Management</a>
                    </c:if>
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h2>Shopping Cart</h2>
            
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="alert alert-success" role="alert">
                    ${msg}
                </div>
            </c:if>

            <c:choose>
                <c:when test="${cart != null && !cart.isEmpty()}">
                    <table class="table table-striped table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th>Mobile ID</th>
                                <th>Mobile Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Year</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cart}" var="mobile">
                                <tr>
                                    <td>${mobile.mobileId}</td>
                                    <td>${mobile.mobileName}</td>
                                    <td>${mobile.description}</td>
                                    <td>$${mobile.price}</td>
                                    <td>${mobile.yearOfProduction}</td>
                                    <td>
                                        <a href="remove-from-cart?mobileId=${mobile.mobileId}" 
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Remove this item from cart?');">
                                            Remove
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                    <div class="mt-3">
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Continue Shopping</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        Your cart is empty.
                    </div>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Go Shopping</a>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
