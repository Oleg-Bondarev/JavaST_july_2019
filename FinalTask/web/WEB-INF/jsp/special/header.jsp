<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 05.10.2019
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionLang}"/>
<fmt:setBundle basename="resources.localization"/>
<c:url value="/WEB-INF/jsp/login.html" var="loginActionURL"/>
<c:url value="/WEB-INF/jsp/logout.html" var="logoutActionURL"/>
<c:url value="/WEB-INF/jsp/home.html" var="titleActionURL"/>
<c:url value="/WEB-INF/jsp/register.html" var="registerActionURL"/>
<c:url value="/WEB-INF/jspuser/profile.html" var="profileActionURL"/>

<header>
    <div>
        <nav class="navbar navbar-expand-md navbar-light">
            <a class="navbar-brand" href="/WEB-INF/jsp/homepage.jsp">
                <img src="" alt="Site logo" aria-describedby="logo-description" class="logo__img">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a class="nav-link" href="<c:url value="/WEB-INF/jsp/coupons.jsp"/>"><fmt:message key="coupons"/><span class="sr-only">(current)</span></a> </li>
                    <li class="nav-item"><a class="nav-link" href="<c:url value="/WEB-INF/jsp/login.jsp"/>"><fmt:message key="login"/><span class="sr-only">(current)</span></a></li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-link dropdown">
                        <form method="post" action="">
                            <select name="lang" class="custom-select-sm float-right">
                                <option value="en_US"><fmt:message key="english"/></option>
                                <option value="ru_RU"><fmt:message key="russian"/></option>
                                <option value="de_DE"><fmt:message key="german"/></option>
                            </select>
                            <button class="btn float-left" type="submit"><fmt:message key="changeLanguage"/></button>
                        </form>

                    </li>
                </ul>
            </div>
        </nav>
    </div>
</header>