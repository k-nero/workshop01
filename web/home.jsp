<%-- 
    Document   : home
    Created on : Jan 26, 2026
    Author     : Nero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Home - Mobile Shop</title>
        <%@include file="/common/header.jsp" %>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home">Mobile Shop</a>
                <div class="navbar-nav ms-auto">
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <span class="navbar-text me-3">Welcome, ${sessionScope.user.fullName}</span>
                            <c:if test="${sessionScope.role == 1 || sessionScope.role == 2}">
                                <a class="nav-link" href="staff/mobile-list">Mobile Management</a>
                            </c:if>
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/cart">Cart</a>
                            <a class="nav-link" href="logout">Logout</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" href="login">Login</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h2>Search Mobiles by Price Range</h2>
            
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

            <form method="get" action="home" class="mb-4">
                <div class="row">
                    <div class="col-md-4">
                        <label for="minPrice" class="form-label">Min Price</label>
                        <input type="number" class="form-control" id="minPrice" name="minPrice" 
                               step="0.01" min="0" value="${param.minPrice}">
                    </div>
                    <div class="col-md-4">
                        <label for="maxPrice" class="form-label">Max Price</label>
                        <input type="number" class="form-control" id="maxPrice" name="maxPrice" 
                               step="0.01" min="0" value="${param.maxPrice}">
                    </div>
                    <div class="col-md-4 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary w-100">Search</button>
                    </div>
                </div>
            </form>

            <h3>Available Mobiles</h3>
            <div class="row">
                <c:forEach items="${listMobiles}" var="mobile">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${mobile.mobileName}</h5>
                                <p class="card-text">
                                    <strong>ID:</strong> ${mobile.mobileId}<br>
                                    <strong>Price:</strong> $${mobile.price}<br>
                                    <strong>Description:</strong> ${mobile.description}<br>
                                    <strong>Year:</strong> ${mobile.yearOfProduction}<br>
                                    <strong>Quantity:</strong> ${mobile.quantity}
                                </p>
                                <c:if test="${sessionScope.user != null && sessionScope.user.role == 0}">
                                    <a href="user/add-to-cart?mobileId=${mobile.mobileId}" class="btn btn-primary">Add to Cart</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <c:if test="${empty listMobiles}">
                <div class="alert alert-info" role="alert">
                    No mobiles found.
                </div>
            </c:if>
        </div>
    </body>
</html>
