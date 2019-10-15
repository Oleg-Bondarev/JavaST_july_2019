<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 07.10.2019
  To change this template use File | Settings | File Templates.
--%>
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

    <c:url value="/login.html" var="loginActionURL"/>
    <c:url value="/js/bootstrap.bundle.min.js" var="jsURL"/>
    <script src="${jsURL}" type="text/javascript"></script>
    <c:set var="language" value="${not empty param.language ? param.language : not
     empty language ? language : pageContext.request.locale}" scope="session" />
    <fmt:setLocale value="${language}" />
    <title><fmt:message key="login"/></title>
</head>
<body>
<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <main>
        <div class="container contact div-bg div-pad-bottom" style="margin-bottom: 20px">
            <div class="div-shadow div-login">
                <form action="${loginActionURL}" method="post">
                    <div class="text-center">
                        <h2><b><fmt:message key="login"/></b></h2>
                    </div>
                    <div class="form-group">
                        <label for="validationDefaultUser">
                            <fmt:message key="username"/>
                        </label>
                        <input pattern="^[a-zA-Z0-9]{4,16}$" id="validationDefaultUser"
                               type="text" class="form-control" name="login"
                               placeholder="Enter username" value="${param.login}" required>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">
                            <fmt:message key="password"/>
                        </label>
                        <input pattern="^[a-zA-Z0-9]{8,16}$" id="inputPassword"
                               type="password" class="form-control" name="password"
                               placeholder="Enter password"
                               aria-describedby="passwordHelp" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="loginbutton"/>
                        </button>
                    </div>
                    <br>
                    <c:if test="${not empty message}">
                        <div class="text-center">
                            <label class="text"><fmt:message key="${message}"/></label>
                        </div>
                    </c:if>
                </form>
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
</fmt:bundle>