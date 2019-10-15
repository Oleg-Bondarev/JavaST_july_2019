<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>


<fmt:bundle basename="local">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata|Playfair+Display|Ubuntu:300&display=swap">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logomak_logo.png" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mystyle.css">
    <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">

    <c:url value="/" var="titleActionURL"/>
    <c:url value="/login.html" var="loginActionURL"/>
    <c:url value="/logout.html" var="logoutActionURL"/>
    <c:url value="/register.html" var="registerActionURL"/>
    <c:url value="/user/profile.html" var="profileActionURL"/>

    <c:set var="language" value="${not empty param.language ? param.language : not
        empty language ? language : pageContext.request.locale}" scope="session" />
    <fmt:setLocale value="${language}" />
    <title><fmt:message key="register"  /></title>
</head>
<body lang="${language}">
<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <main>

        <div class="container contact div-bg div-pad-bottom">
            <div class="div-shadow div-login col-md-6">
                <div class="text-center">
                    <h2><b><fmt:message key="registrationForm"  /> </b></h2>
                </div>
                <div>
                    <form class="form" action="${registerActionURL}" method="post" id="contact_form">
                            <c:if test="${not empty successMessage}">
                                <div class="text-info text-center">
                                    <p>Attention: <fmt:message key="${message}"  /> </p>
                                </div>
                            </c:if>

                            <!-- input text-->
                            <div>
                                <label for="firstNameValidation" class="col-md-6">
                                    <fmt:message key="firstName"  />
                                </label>
                                <div class="input-group">
                                    <input required id="firstNameValidation" pattern="^[a-zA-Z]{2,10}$"
                                           name="firstName" placeholder="<fmt:message key="firstName" />"
                                           class="form-control" value="${param.firstname}" type="text">
                                </div>
                                <label for="lastNameValidation" class="col-md-6">
                                    <fmt:message key="lastName" />
                                </label>
                                <div class="input-group">
                                    <input required id="lastNameValidation" pattern="^[a-zA-Z]{2,15}$"
                                           placeholder="<fmt:message key="lastName"  />" class="form-control"
                                           name="secondName" value="${param.lastname}" type="text">
                                </div>
                                <label for="usernameValidation" class="col-md-6">
                                    <fmt:message key="username" />
                                </label>
                                <div class="input-group">
                                    <input name="login" id="usernameValidation" pattern="^[a-zA-Z0-9]{4,16}$"
                                           placeholder="<fmt:message key="username"  />" required
                                           class="form-control" value="${param.login}" type="text">
                                </div>
                                <label for="passwordValidation" class="col-md-6">
                                    <fmt:message key="password"  />
                                </label>
                                <div class="input-group">
                                    <input id="passwordValidation" required name="password" pattern="^[a-zA-Z0-9]{8,16}$"
                                           placeholder="<fmt:message key="password"  />" class="form-control" type="password">
                                </div>
                                <label for="emailValidation" class="col-md-6">
                                    <fmt:message key="email"  />
                                </label>
                                <div class="input-group">
                                    <input required id="emailValidation" name="email" placeholder="<fmt:message key="email"  />"
                                           pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
                                           class="form-control" value="${param.email}" type="text">
                                </div>
                                <label for="phoneValidation" class="col-md-6">
                                    <fmt:message key="contactMobileNumber"  />
                                </label>
                                <div class="input-group">
                                    <input required id="phoneValidation" name="mobilePhone"
                                           placeholder="<fmt:message key="mobileFormat"  />"
                                           class="form-control" pattern="^((25)|(29)|(33)|(44))([0-9]{7}$)"
                                           value="${param.number}" type="text">
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message key="registerButton"  />
                                    </button>
                                    <button type="reset" class="btn btn-primary">
                                        <fmt:message key="reset"  />
                                    </button>
                                </div>
                            </div>
                    </form>
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
</fmt:bundle>
