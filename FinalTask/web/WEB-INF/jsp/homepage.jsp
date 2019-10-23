<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>

<c:url value="/WEB-INF/jsp/login.html" var="loginActionURL"/>
<c:url value="/WEB-INF/jsp/register.html" var="registerActionURL"/>
<c:url value="/WEB-INF/jsp/logout.html" var="logoutActionURL"/>
<c:url value="/WEB-INF/jsp/user/profile.html" var="profileActionURL"/>
<c:set var="language" value="${not empty param.language ? param.language : not
 empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
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
    <title><fmt:message key="title" bundle="${lang}"/></title>

    <c:url value="/coupons.html?page=1" var="couponsActionURL"/>
</head>
<body>

<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <br>
    <c:if test="${not empty message}">
        <div style="text-align: center;">
            <label class="text"><fmt:message key="${message}"  bundle="${lang}"/></label>
        </div>
    </c:if>
    <main>
        <!-- Jumbotron -->
        <div class="container">
            <div class="jumbotron jumbotron-cover-image text-center">
                <div class="container">
                    <h1><fmt:message key="bannerTitle" bundle="${lang}"/></h1>
                    <h2><fmt:message key="bannerSlogan" bundle="${lang}"/></h2>
                </div>
                <div>
                    <a class="btn btn-primary btn-lg jumbotron-button"
                       href="${couponsActionURL}" role="button">
                        <fmt:message key="coupons" bundle="${lang}"/>
                    </a>
                </div>
            </div>
        </div>
        <!-- Jumbotron -->

        <div class="container contact div-bg div-pad-bottom div-shadow" style="margin-bottom: 20px">
            <div class="row h2-pad-top">
                <div class="col-sm-6">
                    <div class="card height-body-card contact-card">
                        <div class="card-body div-bg2">
                            <h2 class="card-title text-center"><fmt:message key="ourContacts" bundle="${lang}"/></h2>
                            <form class="p-5">
                                <div class="md-form form-sm">
                                    <div>
                                        <fmt:message key="addressTitle" bundle="${lang}"/>:
                                        <fmt:message key="addressStreet" bundle="${lang}"/>
                                    </div>
                                    <div><fmt:message key="postIndexTitle" bundle="${lang}"/>: 1000645</div>
                                    <div><fmt:message key="workingHoursTitle" bundle="${lang}"/>: 9:00-18:00</div>
                                    <div><fmt:message key="phoneTitle" bundle="${lang}"/>: <a href="tel:+375336494933">+375 (33) 649-49-33</a></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="card height-body-card contact-card">
                        <div class="card-body div-bg2">
                            <h2 class="card-title text-center"><fmt:message key="socialNetworks" bundle="${lang}"/></h2>
                            <form class="p-5">
                                <div class="md-form form-sm">
                                    <div><fmt:message key="vkTitle" bundle="${lang}"/>: <a href="https://vk.com/oleg_bondarev">vk</a> </div>
                                    <div>Facebook: <a href="https://ru-ru.facebook.com/">fb</a></div>
                                    <div>Twitter: <a href="https://twitter.com/">tw</a></div>
                                    <div>Instagram: <a href="https://www.instagram.com/">inst</a> </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container contact div-bg div-pad-bottom div-shadow" style="margin-bottom: 20px">
            <div class="col-sm-12">
                <script type="text/javascript" charset="utf-8" async src="https://api-maps.yandex.ru/services/constructor/1.0/js/?um=constructor%3A00cfac897af2b7d4e3ed33bc02ae39984d95f266b753099727e8f8b3500c0058&amp;width=100%&amp;height=581&amp;lang=ru_RU&amp;scroll=true"></script>
            </div>
        </div>
    </main>
</div>

<%--<jsp:include page="/WEB-INF/jsp/special/footer.jsp" flush="true"/>--%>
<ctgg:footer/>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>