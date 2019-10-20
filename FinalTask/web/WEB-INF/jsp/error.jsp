<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 06.10.2019
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logomak_logo.png" type="image/x-icon">

    <c:url value="/WEB-INF/jsp/login.html" var="loginActionURL"/>
    <c:url value="/WEB-INF/jsp/register.html" var="registerActionURL"/>
    <c:url value="/WEB-INF/jsp/logout.html" var="logoutActionURL"/>
    <c:url value="/WEB-INF/jsp/user/profile.html" var="profileActionURL"/>
    <c:set var="language" value="${not empty param.language ? param.language : not
        empty language ? language : pageContext.request.locale}" scope="session" />
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="local" var="lang"/>
    <title><fmt:message key="title" bundle="${lang}"/></title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata|Playfair+Display|Ubuntu:300&display=swap">
    <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/mystyle.css" rel="stylesheet">
</head>

<body lang="${language}">
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
                <label class="text text-danger">
                    <fmt:message key="${message}" bundle="${lang}"/></label>
            </div>
        </c:if>
    </main>
</div>
<ctgg:footer/>
<%--<jsp:include page="/WEB-INF/jsp/special/footer.jsp" flush="true"/>--%>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>