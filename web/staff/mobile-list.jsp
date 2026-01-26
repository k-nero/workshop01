<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Mobile Management</title>
        <%@include file="../common/header.jsp" %>
    </head>
    <body>
        <jsp:include page="header_staff.jsp" />

        <div class="container mt-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h3>Manage Mobiles</h3>
                <a href="create-mobile" class="btn btn-success">Add New Mobile</a>
            </div>
            
            <c:if test="${not empty msg}">
                <div class="alert alert-success" role="alert">
                    ${msg}
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.msg}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.msg}
                </div>
                <c:remove var="msg" scope="session"/>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.error}">
                <div class="alert alert-danger" role="alert">
                    ${sessionScope.error}
                </div>
                <c:remove var="error" scope="session"/>
            </c:if>

            <form method="get" action="mobile-list" class="mb-3">
                <div class="row">
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="search" 
                               placeholder="Search by Mobile ID or Name" value="${searchTerm}">
                    </div>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-primary w-100">Search</button>
                    </div>
                </div>
            </form>

            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Mobile ID</th>
                        <th>Mobile Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Year</th>
                        <th>Quantity</th>
                        <th>Not Sale</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listMobiles}" var="mobile">
                        <tr>
                            <td>${mobile.mobileId}</td>
                            <td>${mobile.mobileName}</td>
                            <td>${mobile.description}</td>
                            <td>$${mobile.price}</td>
                            <td>${mobile.yearOfProduction}</td>
                            <td>${mobile.quantity}</td>
                            <td>${mobile.notSale ? 'Yes' : 'No'}</td>
                            <td>
                                <a href="update-mobile?mobileId=${mobile.mobileId}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="delete-mobile?mobileId=${mobile.mobileId}" 
                                   class="btn btn-danger btn-sm" 
                                   onclick="return confirm('Are you sure to delete this mobile?');">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <c:if test="${empty listMobiles}">
                <div class="alert alert-info" role="alert">
                    No mobiles found.
                </div>
            </c:if>
        </div>
    </body>
</html>
