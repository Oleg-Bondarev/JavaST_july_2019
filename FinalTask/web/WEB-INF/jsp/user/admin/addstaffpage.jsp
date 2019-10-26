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
    <title><fmt:message key="addPersonalTitle" bundle="${lang}"/></title>

    <c:url value="/user/admin/addstaff.html" var="staffAddURL"/>
</head>
<body>
    <div id="wrap">
        <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
        <main>
            <form class="form" action="${staffAddURL}" method="post" id="contact_form">
                <div class="container contact div-bg div-pad-bottom">
                    <div class="card div-shadow">
                        <div class="card-header">
                            <h2 class="text-center"><fmt:message key="addPersonalTitle" bundle="${lang}"/> </h2>
                        </div>
                        <c:if test="${not empty successMessage}">
                            <div class="text-center text-info">
                                <p><fmt:message key="${successMessage}" bundle="${lang}"/></p>
                            </div>
                        </c:if>
                        <c:if test="${not empty message}">
                            <div class="text-center text-warning">
                                <p><fmt:message key="attantion" bundle="${lang}"/>:
                                    <fmt:message key="${message}" bundle="${lang}"/>
                                </p>
                            </div>
                        </c:if>
                        <div class="card-body form-group">
                            <label><fmt:message key="firstName" bundle="${lang}"/> </label>
                            <input name="firstName" class="form-control" type="text" min="2" max="10" pattern="^[a-zA-Zа-яА-ЯёЁ-]+$" required>
                            <label><fmt:message key="lastName" bundle="${lang}"/></label>
                            <input name="secondName" class="form-control"  type="text" min="2" max="15" pattern="[a-zA-Zа-яА-ЯёЁ-]+$" required>
                            <label><fmt:message key="username" bundle="${lang}"/></label>
                            <input name="login" class="form-control" type="text" min="4" max="16" pattern="^[a-zA-Z0-9]+$" required>

                            <label><fmt:message key="password" bundle="${lang}"/></label>
                            <input name="password" class="form-control" type="password" min="8" max="16" pattern="^[a-zA-Z0-9]+$" required>

                            <label><fmt:message key="email" bundle="${lang}"/></label>
                            <input name="email" class="form-control" type="text" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" required>
                            <label><fmt:message key="phoneTitle" bundle="${lang}"/></label>
                            <input name="mobilePhone" class="form-control" type="text" pattern="^((25)|(29)|(33)|(44))([0-9]{7}$)" required>
                            <div>
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="addStaff" bundle="${lang}"/>
                                </button>
                                <button type="reset" class="btn btn-primary">
                                    <fmt:message key="reset" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </main>
    </div>

    <ctgg:footer/>

    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

