<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not
 empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:bundle basename="local">
<c:url value="/loginpage.html" var="loginPageActionURL"/>
<c:url value="/logout.html" var="logoutActionURL"/>
<c:url value="/homepage.html" var="titleActionURL"/>
<c:url value="/registration.html" var="registrationPageActionURL"/>
<c:url value="/coupons.html?page=1" var="couponsActionURL"/>
<c:url value="/user/staff/addcouponpage.html" var="addCouponActionURL"/>
<c:url value="/coupon/findpurchase.html" var="findPurchaseActionURL"/>
<c:url value="/user/profile.html" var="profileActionURL"/>
<c:url value="/user/admin/findstaff.html?page=1" var="findstaffActionURL"/>
<c:url value="/user/admin/findusers.html?page=1" var="allUsersActionURL"/>
<c:url value="/coupon/user/mypurchases.html?page=1" var="myCouponsActionURL"/>
<c:url value="/companyprovider/findcompany.html?page=1" var="findCompanyPageActionURL"/>

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
                    <li class="nav-item">
                        <a class="nav-link" href="${couponsActionURL}">
                            <fmt:message key="coupons" /><span class="sr-only">(current)</span>
                        </a>
                    </li>

                    <c:if test="${not empty authorizedUser}">

                            <c:if test="${authorizedUser.role == 'ADMIN'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${findstaffActionURL}"><fmt:message key="findStaff" /></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${allUsersActionURL}"><fmt:message key="allUsers" /><span class="sr-only">(current)</span> </a>
                                </li>
                            </c:if>

                            <c:if test="${authorizedUser.role == 'STAFF'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${addCouponActionURL}">
                                        <fmt:message key="addCoupon" /><span class="sr-only">(current)</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${findCompanyPageActionURL}">
                                        <fmt:message key="companies"/> <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${authorizedUser.role == 'USER'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${myCouponsActionURL}">
                                        <fmt:message key="findPurchase" /><span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            </c:if>

                        <li class="nav-item">
                            <a class="nav-link" href="${profileActionURL}">
                                <fmt:message key="profileTitle" />
                                (${authorizedUser.login})
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${logoutActionURL}">
                                <fmt:message key="logout" />
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${empty authorizedUser}">
                        <li class="nav-item">
                            <a class="nav-link" href="${loginPageActionURL}">
                                <fmt:message key="login" /><span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${registrationPageActionURL}">
                                <fmt:message key="register" /><span class="sr-only">(current)</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-link dropdown">
                        <form action="">
                            <select name="language" onchange="submit()">
                                <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="russian" /></option>
                                <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="english" /></option>
                                <option value="de" ${language == 'de' ? 'selected' : ''}><fmt:message key="german" /></option>
                            </select>
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
        ${exception}
    </div>
</header>
</fmt:bundle>