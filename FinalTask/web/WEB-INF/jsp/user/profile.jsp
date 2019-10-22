<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>

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

    <script src="${jsURL}" type="text/javascript"></script>
    <c:set var="language" value="${not empty param.language ? param.language : not
     empty language ? language : pageContext.request.locale}" scope="session" />
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="local" var="lang"/>
    <title><fmt:message key="profileTitle" bundle="${lang}"/></title>

    <c:url value="/user/usereditpage.html" var="userEditPageURL"/>
    <c:url value="/coupon/user/mypurcases.html?page=1" var="mypurchasesPageAction"/>
</head>

<body>
    <div id="wrap">
        <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
        <main>
            <c:if test="${not empty message}">
                <div class="text-center">
                    <label class="text">
                        <fmt:message key="${message}" bundle="${lang}"/>
                    </label>
                </div>
            </c:if>

            <div class="row">
                <div class="col-sm-6 div-shadow">
                    <div class="card  user-avatar-div">
                        <div class="card-body text-center">
                            <img class="card-img-top profile-avatar"
                                 src="<%=request.getContextPath()%>/${user.pathToAvatar}" alt="User avatar">
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 div-shadow" style="display: grid;">
                    <div class="card">
                        <div class="card-body">
                            <div class="profile-div">
                                <h5 class="card-title text-center"><fmt:message key="userCredentialsData" bundle="${lang}"/></h5>
                                <p class="card-text">
                                    <fmt:message key="username" bundle="${lang}"/>: ${user.login}<br>
                                    <fmt:message key="userRole" bundle="${lang}"/>: ${user.role}<br>
                                </p>
                            </div>
                            <div class="profile-div">
                                <h5 class="card-title text-center"><fmt:message key="userInformation" bundle="${lang}"/></h5>
                                <p class="card-text">
                                    <fmt:message key="firstName" bundle="${lang}"/>: ${user.firstName}<br>
                                    <fmt:message key="lastName" bundle="${lang}"/>: ${user.secondName}<br>
                                    <fmt:message key="email" bundle="${lang}"/>: ${user.email}<br>
                                    <fmt:message key="phoneTitle" bundle="${lang}"/>: +375${user.mobilePhone}<br>
                                    <fmt:message key="registrationDate" bundle="${lang}"/>: ${user.registrationDate}
                                </p>
                            </div>

                            <div class="profile-button-div row">
                                <a class="btn btn-primary button-a" href="${userEditPageURL}">
                                    <fmt:message key="editProfilButton" bundle="${lang}"/>
                                </a>
                                <c:if test="${authorizedUser.role == 'USER'}">
                                    <a href="${mypurchasesPageAction}" class="btn btn-primary button-a">
                                        <fmt:message key="myPurchasesButton" bundle="${lang}"/>
                                    </a>
                                </c:if>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <ctgg:footer/>

    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>