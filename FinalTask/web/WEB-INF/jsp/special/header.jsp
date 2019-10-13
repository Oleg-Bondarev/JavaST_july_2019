<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not
 empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="lang"/>
<c:url value="/login.html" var="loginActionURL"/>
<c:url value="/logout.html" var="logoutActionURL"/>
<c:url value="/homepage.html" var="titleActionURL"/>
<c:url value="/register.html" var="registerActionURL"/>
<c:url value="/user/profile.html" var="profileActionURL"/>
<c:url value="/coupons.html?page=1" var="couponsActionURL"/>
<c:url value="/user/admin/addadmin.html" var="addadminActionURL"/>
<c:url value="/user/admin/findadmin.html?page=1" var="findadminActionURL"/>
<c:url value="/coupon/addcoupon.html" var="addCouponActionURL"/>
<c:url value="/coupon/findpurchase.html" var="findPurchaseActionURL"/>
<c:url value="/user/mycoupons.html?page=1" var="myCouponsActionURL"/>

<header lang="${language}">
    <div>
        <nav class="navbar navbar-expand-md navbar-light">
            <a class="navbar-brand" href="${titleActionURL}">
                <img src="<%=request.getContextPath()%>/img/logo1.png" alt="Site logo" aria-describedby="logo-description" class="logo__img">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a class="nav-link" href="${couponsActionURL}"><fmt:message key="coupons" bundle="${lang}"/><span class="sr-only">(current)</span></a> </li>

                    <c:if test="${not empty authorizedUser}">
                        <c:choose>
                            <c:when test="${authorizedUser.userRole == 'ADMIN'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${findadminActionURL}"><fmt:message key="findAdmin" bundle="${lang}"/></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${addadminActionURL}"><fmt:message key="addAdmin" bundle="${lang}"/> </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${addCouponActionURL}"><fmt:message key="addCoupon" bundle="${lang}"/><span class="sr-only">(current)</span> </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${findPurchaseActionURL}"><fmt:message key="findPurchase" bundle="${lang}"/><span class="sr-only">(current)</span> </a>
                                </li>
                            </c:when>
                            <c:when test="${authorizedUser.userRole == 'USER'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${myCouponsActionURL}">
                                        <fmt:message key="findPurchase" bundle="${lang}"/><span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            </c:when>
                        </c:choose>
                        <li class="nav-item">
                            <a class="nav-link" href="${profileActionURL}">
                                <fmt:message key="profile" bundle="${lang}"/>
                                (${authorizedUser.username})
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${logoutActionURL}">
                                <fmt:message key="logout" bundle="${lang}"/>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${empty authorizedUser}">
                        <li class="nav-item">
                            <a class="nav-link" href="${loginActionURL}">
                                <fmt:message key="login" bundle="${lang}"/><span class="sr-only">(current)</span>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${registerActionURL}">
                                <fmt:message key="register" bundle="${lang}"/><span class="sr-only">(current)</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-link dropdown">
                        <form action="">
                            <select name="language" onchange="submit()">
                                <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="russian" bundle="${lang}"/></option>
                                <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="english" bundle="${lang}"/></option>
                                <option value="de" ${language == 'en' ? 'selected' : ''}><fmt:message key="german" bundle="${lang}"/></option>
                            </select>
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
        ${exception}
    </div>
</header>
