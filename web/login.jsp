<%-- 
    Document   : login
    Created on : Jan 26, 2026
    Author     : Nero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@include file="/common/header.jsp" %>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center align-items-center" style="min-height: 100vh;">
                <div class="col-md-6 col-lg-5">
                    <div class="card shadow-sm border-0">
                        <div class="card-header bg-dark text-white text-center">
                            <h3 class="mb-0">Mobile Shop Login</h3>
                        </div>
                        <div class="card-body p-4">
                            <form action="login" method="post">
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

                                <div class="mb-3">
                                    <label for="userId" class="form-label">User ID</label>
                                    <input type="text" class="form-control" 
                                           id="userId" name="userId" 
                                           value="${param.userId}"
                                           required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" class="form-control" 
                                           id="password" name="password" 
                                           required>
                                    <small class="form-text text-muted">Password must be a number</small>
                                </div>

                                <button type="submit" class="btn btn-primary w-100 mt-3">
                                    Login
                                </button>

                                <div class="text-center mt-3">
                                    <a href="${pageContext.request.contextPath}/home" class="text-decoration-none">
                                        Go back to homepage
                                    </a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
