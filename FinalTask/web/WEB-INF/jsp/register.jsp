<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata|Playfair+Display|Ubuntu:300&display=swap">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logomak_logo.png" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mystyle.css">
    <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">

    <c:url value="/" var="titleActionURL"/>
    <c:url value="/WEB-INF/jsp/login.html" var="loginActionURL"/>
    <c:url value="/WEB-INF/jsp/logout.html" var="logoutActionURL"/>
    <c:url value="/WEB-INF/jsp/register.html" var="registerActionURL"/>
    <c:url value="/WEB-INF/jsp/user/profile.html" var="profileActionURL"/>
    
    <fmt:setLocale value="${sessionLang}"/>
    <fmt:setBundle basename="by.training.final_task.resource.localization"/>
    <title><fmt:message key="register"/></title>
</head>
<body>
<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <main>
        <div class="container contact div-bg div-pad-bottom" style="margin-bottom: 20px">
            <form class="form" action="" method="post" id="contact_form">
                <fieldset>
                    <legend class="text-center">
                        <h2><b><fmt:message key="registrationForm"/> </b></h2>
                    </legend>
                    <br>
                    <c:if test="${not empty successMessage}">
                        <div class="text-info text-center">
                            <p>Attention: <fmt:message key="${message}"/> </p>
                        </div>
                    </c:if>

                    <!-- input text-->
                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label for="firstNameValidation" class="col-md-6">
                                <fmt:message key="firstName"/>
                            </label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input required id="firstNameValidation" pattern="^[a-zA-Z]{2,10}$"
                                       name="firstname" placeholder="<fmt:message key="firstName"/>"
                                       class="form-control" value="${param.firstname}" type="text">
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="lastNameValidation" class="col-md-6">
                                <fmt:message key="lastName"/>
                            </label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input required id="lastNameValidation" pattern="^[a-zA-Z]{2,15}$"
                                        placeholder="<fmt:message key="lastName"/>" class="form-control"
                                        name="lastname" value="${param.lastname}" type="text">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label for="usernameValidation" class="col-md-6">
                                <fmt:message key="username"/>
                            </label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input name="username" id="usernameValidation" pattern="^[a-zA-Z0-9]{4,16}$"
                                        placeholder="<fmt:message key="username"/>" required
                                        class="form-control" value="${param.login}" type="text">
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="passwordValidation" class="col-md-6">
                                <fmt:message key="password"/>
                            </label>
                            <div class="input-group">
                                <input id="passwordValidation" required name="password" pattern="^[a-zA-Z0-9]{8,16}$"
                                    placeholder="<fmt:message key="password"/>" class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="emailValidation" class="col-md-6">
                                <fmt:message key="email"/>
                            </label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input required id="emailValidation" name="email" placeholder="<fmt:message key="email"/>"
                                        pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
                                        class="form-control" value="${param.email}" type="text">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="phoneValidation" class="col-md-6">
                                <fmt:message key="contactMobileNumber"/>
                            </label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input required id="phoneValidation" name="contactnumber"
                                        placeholder="<fmt:message key="mobileFormat"/>"
                                        class="form-control" pattern="^((25)|(29)|(33)|(44))([0-9]{7}$)"
                                        value="${param.number}" type="text">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-row mx-auto">
                        <div class="form-group col-md-5 text-center">
                            <br>
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="registerButton"/>
                            </button>
                        </div>
                        <div class="form-group col-md-2">
                            <br>
                            <button type="reset" class="btn btn-primary">
                                <fmt:message key="reset"/>
                            </button>
                        </div>
                    </div>
                </fieldset>
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
