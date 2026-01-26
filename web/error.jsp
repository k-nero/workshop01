<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error</title>
        <%@include file="/common/header.jsp" %>
    </head>
    <body>
        <div class="container mt-5">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">Error!</h4>
                <p>An error occurred. Please try again later.</p>
                <hr>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Go to Home</a>
            </div>
        </div>
    </body>
</html>
