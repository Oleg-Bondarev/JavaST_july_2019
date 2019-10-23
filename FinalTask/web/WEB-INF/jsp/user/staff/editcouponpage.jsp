<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 23.10.2019
  To change this template use File | Settings | File Templates.
--%>
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

    <title><fmt:message key="editCouponTitle" bundle="${lang}"/></title>

    <c:url value="/user/staff/edditcoupon.html" var="usereditActionURL"/>
</head>
<body>
<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <main>
        <div class="conteiner">
            <form method="post" action="${usereditActionURL}">
                <c:if test="${not empty successMessage}">
                    <div class="text-center text-info">
                        <p><fmt:message key="attantion" bundle="${lang}"/>:
                            <fmt:message key="${successMessage}" bundle="${lang}"/></p>
                    </div>
                </c:if>
                <c:if test="${not empty message}">
                    <div class="text-center text-warning">
                        <p><fmt:message key="attantion" bundle="${lang}"/>:
                            <fmt:message key="${message}"/> </p>
                    </div>
                </c:if>

                <div class="container contact div-bg div-pad-bottom">
                    <div class="card div-shadow">
                        <div class="card-header">
                            <h2 class="text-center">
                                <fmt:message key="editCouponTitle" bundle="${lang}"/> ${oldCoupon.couponName}
                            </h2>
                        </div>
                        <div class="card-body form-group">
                            <input type="hidden" name="couponID" value="${oldCoupon.id}">
                            <label><fmt:message key="couponName" bundle="${lang}"/> </label>
                            <input pattern="^[a-zA-Zа-яА-Я\d- ]{3,20}$" name="couponName"
                                   value="${oldCoupon.couponName}"
                                   class="form-control" type="text" required>

                            <label><fmt:message key="couponPrice" bundle="${lang}"/></label>
                            <input name="couponPrice" class="form-control" type="text"
                                   value="${oldCoupon.couponPrice}" required>

                            <label><fmt:message key="locationAddress" bundle="${lang}"/></label>
                            <input name="holdingAddress" class="form-control" type="text"
                                   value="${oldCoupon.holdingAddress}" required>

                            <label><fmt:message key="couponCategory" bundle="${lang}"/></label>
                            <select required name="category" class="form-control">
                                <c:forEach items="${categoryList}" var="i">
                                    <option value="${i.id}" <c:if
                                        test="${oldCoupon.category.id == i.id}">
                                        selected</c:if>>
                                            ${i.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <label><fmt:message key="couponProvider" bundle="${lang}"/></label>
                            <select required name="companyProvider" class="form-control">
                                <c:forEach items="${companyList}" var="i">
                                    <option value="${i.id}" <c:if
                                            test="${oldCoupon.companyProvider.id == i.id}">
                                        selected</c:if>>
                                            ${i.companyName}
                                    </option>
                                </c:forEach>
                            </select>
                            <label><fmt:message key="couponDescription" bundle="${lang}"/></label>
                            <textarea placeholder="<fmt:message key="descriptionPlaceholder" bundle="${lang}"/>"
                                      name="couponDescription" class="form-control"  type="text" required>${oldCoupon.couponDescription}</textarea>

                            <div>
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="editButton" bundle="${lang}"/>
                                </button>
                                <button type="reset" class="btn btn-primary">
                                    <fmt:message key="reset" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </main>
</div>

<ctgg:footer/>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

