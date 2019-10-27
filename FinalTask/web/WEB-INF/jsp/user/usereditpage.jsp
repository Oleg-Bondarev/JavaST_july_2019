<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="local" var="lang"/>
<html lang="${language}">
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logomak_logo.png" type="image/x-icon">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata|Playfair+Display|Ubuntu:300&display=swap">
    <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mystyle.css">
    <c:set var="language" value="${not empty param.language ? param.language : not
         empty language ? language : pageContext.request.locale}" scope="session" />
    <fmt:setLocale value="${language}" />
    <title><fmt:message key="userEditTitle" bundle="${lang}"/></title>

    <c:url value="/user/useredit.html" var="usereditActionURL"/>
</head>
<body>

<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <main>
        <div class="conteiner">
            <form method="post" action="${usereditActionURL}">
                <c:if test="${not empty successMessage}">
                    <div class="text-center text-info">
                        <p><fmt:message key="attantion" bundle="${lang}"/>:
                            <fmt:message key="${successMessage}" bundle="${lang}"/></p>
                    </div>
                </c:if>

                <div class="container contact div-bg div-pad-bottom">
                    <div class="card div-shadow">
                        <div class="card-header">
                            <h2 class="text-center"><fmt:message key="userEditTitle" bundle="${lang}"/> ${oldUserInformation.login}</h2>
                        </div>

                        <c:if test="${not empty message}">
                            <div class="text-center text-warning">
                                <p><fmt:message key="attantion" bundle="${lang}"/>:
                                    <fmt:message key="${message}" bundle="${lang}"/>
                                </p>
                            </div>
                        </c:if>

                        <div class="card-body">
                            <label><fmt:message key="firstName" bundle="${lang}"/></label>
                            <input name="firstName" class="form-control" value="${oldUserInformation.firstName}" type="text">
                            <label><fmt:message key="lastName" bundle="${lang}"/></label>
                            <input name="secondName" class="form-control" value="${oldUserInformation.secondName}" type="text">
                            <label><fmt:message key="username" bundle="${lang}"/></label>
                            <input name="login" class="form-control" value="${authorizedUser.login}" type="text">
                            <input type="hidden" name="oldLogin" value="${authorizedUser.login}">
                            <!--Check password-->
                            <label><fmt:message key="password" bundle="${lang}"/></label>
                            <input name="password" class="form-control" type="password">

                            <label><fmt:message key="email" bundle="${lang}"/></label>
                            <input name="email" class="form-control" value="${oldUserInformation.email}" type="text">
                            <label><fmt:message key="phoneTitle" bundle="${lang}"/></label>
                            <input name="mobilePhone" class="form-control" value="+375${oldUserInformation.mobilePhone}" type="text">
                            <div>
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="editProfilButton" bundle="${lang}"/>
                                </button>
                                <button type="reset" class="btn btn-primary">
                                    <fmt:message key="reset" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </main>
</div>

<ctgg:footer/>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
