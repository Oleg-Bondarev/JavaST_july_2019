<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 06.10.2019
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <c:url value="/WEB-INF/jsp/login.html" var="loginActionURL"/>
    <c:url value="/WEB-INF/jsp/register.html" var="registerActionURL"/>
    <c:url value="/WEB-INF/jsp/logout.html" var="logoutActionURL"/>
    <c:url value="/WEB-INF/jsp/user/profile.html" var="profileActionURL"/>
    <fmt:setLocale value="${sessionLang}"/>
    <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
    <title><fmt:message key="title"/></title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/mystyle.css" rel="stylesheet">
</head>

<body>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <main>
        <div class="text-center">
            <h4 class="text-center">Error 404, Page not Found</h4>
        </div>
        <br>
        <c:if test="${not empty message}">
            <div style="text-align: center;">
                <label class="text text-danger" for="navbarResponsive">
                    <fmt:message key="${message}"/></label>
            </div>
        </c:if>
    </main>
</div>
<jsp:include page="/WEB-INF/jsp/special/footer.jsp" flush="true"/>

</body>
</html>