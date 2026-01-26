<%-- 
    Document   : header_staff
    Created on : Jan 26, 2026
    Author     : Nero
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="mobile-list">Mobile Management</a>
        <div class="navbar-nav ms-auto">
            <span class="navbar-text me-3">Welcome, ${sessionScope.user.fullName}</span>
            <a class="nav-link" href="${pageContext.request.contextPath}/staff/mobile-list">Mobile Management</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
</nav>
