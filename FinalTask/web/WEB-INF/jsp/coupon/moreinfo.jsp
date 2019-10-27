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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tableadoptation.css">

    <script src="${jsURL}" type="text/javascript"></script>
    <c:set var="language" value="${not empty param.language ? param.language : not
     empty language ? language : pageContext.request.locale}" scope="session" />
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="local" var="lang"/>
    <title><fmt:message key="couponTitle" bundle="${lang}"/></title>

    <c:url value="/user/staff/editcouponpage.html" var="editCouponPageURL"/>
    <c:url value="/user/buycoupon.html" var="buyCouponAction"/>
    <c:url value="/user/staff/blockcoupon.html" var="blockCouponAction"/>
</head>
<body>
<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <main>

        <c:if test="${not empty message}">
            <div class="text-center text-warning">
                <label class="text">
                    <fmt:message key="attantion" bundle="${lang}"/>:
                    <fmt:message key="${message}" bundle="${lang}"/>
                </label>
            </div>
        </c:if>

        <div class="container div-shadow" style="background-color: white">
            <div class="text-center">
                <img class="card-img-top profile-avatar"
                     src="<%=request.getContextPath()%>/${coupon.pathToPicture}"
                     alt="Coupon picture">
            </div>
            <div class="text-center coupon-name">
                <h1>
                    ${coupon.couponName}
                </h1>
            </div>
            <div class="row text-center">
                <div class="col-md-6">
                    <h3>
                        <fmt:message key="couponPrice" bundle="${lang}"/>:
                        ${coupon.couponPrice}
                    </h3>
                </div>
                <div class="col-md-6">
                    <h3>
                        <fmt:message key="couponAddDate" bundle="${lang}"/>:
                        ${coupon.couponAddDate}
                    </h3>
                </div>
            </div>
            <div class="row text-center">
                <div class="col-md-6">
                    <h3>
                        <fmt:message key="couponCategory" bundle="${lang}"/>:
                        ${coupon.category.name}
                    </h3>
                </div>
                <div class="col-md-6">
                    <h4>
                        <fmt:message key="couponProvider" bundle="${lang}"/>:
                        ${coupon.companyProvider.companyName}
                    </h4>
                </div>
            </div>
            <div class="row" style="margin: 30px;">
               <h4>
                   &nbsp&nbsp&nbsp&nbsp${coupon.couponDescription}
               </h4>
            </div>
            <div class="row" style="justify-content: center">
                <c:if test="${authorizedUser.role != 'STAFF' and authorizedUser.role != 'ADMIN'}">
                    <form action="${buyCouponAction}" method="post">
                        <input type="hidden" name="couponID" value="${coupon.id}">
                        <button class="btn btn-primary coupon-profile-button" type="submit" >
                            <fmt:message key="buyCouponButton" bundle="${lang}"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${authorizedUser.role == 'STAFF'}">
                    <div class="col-md-6 text-center">
                        <form action="${editCouponPageURL}" method="post">
                            <input type="hidden" name="couponID" value="${coupon.id}">
                            <button class="btn btn-primary coupon-profile-button" type="submit">
                                <fmt:message key="editButton" bundle="${lang}"/>
                            </button>
                        </form>
                    </div>
                    <div class="col-md-6 text-center">
                        <form action="${blockCouponAction}" method="post">
                            <input type="hidden" name="couponID" value="${coupon.id}">
                            <button class="btn btn-primary coupon-profile-button" type="submit">
                                <fmt:message key="blockButton" bundle="${lang}"/>
                            </button>
                        </form>
                    </div>
                </c:if>
            </div>

        </div>
    </main>
</div>

<ctgg:footer/>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
