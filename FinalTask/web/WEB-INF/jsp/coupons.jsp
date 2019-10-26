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

    <c:set var="language" value="${not empty param.language ? param.language : not
        empty language ? language : pageContext.request.locale}" scope="session" />
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="local" var="lang"/>
    <title><fmt:message key="coupons"  bundle="${lang}"/></title>

    <c:url value="/coupons.html?page=1" var="allCouponsPageURL"/>
    <%--<c:url value="/coupon/moreinfo.html" var="moreInfoPageURL"/>--%>
    <c:url value="/coupon/findbycategory.html?page=1" var="findByCategoryAction"/>
    <c:url value="/coupon/findbyprice.html?page=1" var="findByPriceAction"/>
</head>

<body>

<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <br>
    <c:if test="${not empty message}">
        <div style="text-align: center;">
            <label class="text"><fmt:message key="${message}" bundle="${lang}"/></label>
        </div>
    </c:if>
    <main>
        <div class="row">
            <div class="col-md-6 align-content-center">
                <form class="form-inline" method="post" action="${findByPriceAction}">
                    <div class="form-group">
                        <div>
                            <input class="form-control mr-sm-2" name="minPriceRange"
                                   type="search" placeholder="<fmt:message key="minPricePlaceholder" bundle="${lang}"/>"
                                   value="${sessionScope.minPriceRange}" pattern="^[1-9]{1}[\d]{0,2}\.[\d]{2}$">
                        </div>
                        <div>
                            <input class="form-control mr-sm-2" name="maxPriceRange"
                                   type="search" placeholder="<fmt:message key="maxPricePlaceholder" bundle="${lang}"/>"
                                   value="${sessionScope.maxPriceRange}" pattern="^[1-9]{1}[\d]{0,2}\.[\d]{2}$">
                        </div>
                        <div>
                            <button class="btn btn-outline-success" type="submit">
                                <fmt:message key="searchButton" bundle="${lang}"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-4 justify-content-center">
                <form class="form-inline" method="post" action="${findByCategoryAction}">
                    <div class="form-group">
                        <label>
                            <select required name="category" class="form-control">
                                <c:forEach items="${categoryList}" var="i">
                                    <option value="${i.id}">
                                            ${i.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                        <div>
                            <button class="btn btn-outline-success" type="submit">
                                <fmt:message key="searchButton" bundle="${lang}"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-2 justify-content-center">
                <form class="col-md-6" action="${allCouponsPageURL}" method="post">
                    <button class="btn btn-outline-success" type="submit" >
                        <fmt:message key="allCoupons" bundle="${lang}"/>
                    </button>
                </form>
            </div>
        </div>

        <c:if test="${not empty message}">
            <div class="text-center">
                <fmt:message key="${message}" bundle="${lang}"/>
            </div>
        </c:if>

        <div class="row" style="justify-content: center">
            <c:forEach items="${resultCoupons}" var="coup">
                <div class="card" style="width: 18rem;">
                    <form action="<c:url value="/coupon/moreinfo.html?id=${coup.id}"/>" method="post">
                        <img class="card-img-top photo-padding"
                             src="<%=request.getContextPath()%>/${coup.pathToPicture}"
                             alt="Coupon avatar">
                        <div class="card-body text-center">
                            <h5 class="card-title text-center">
                                <c:out value="${coup.couponName}"/><br>
                            </h5>
                            <p>
                                <fmt:message key="couponPrice" bundle="${lang}"/>:
                                <c:out value="${coup.couponPrice}"/><br>
                            </p>
                            <input type="hidden" name="couponID" value="${coup.id}">
                            <input type="submit" value="<fmt:message key="moreInfoButton" bundle="${lang}"/>"
                                   class="btn-sm btn-primary">
                        </div>
                    </form>
                </div>
            </c:forEach>
        </div>

        <div class="row">
            <div class="col-sm-12 mx-auto">
                <ul class="pagination" style="justify-content: center">
                    <c:if test="${param.page > 1}">
                        <li class="page-item">
                            <a class="page-link" href="<c:url value="${paginationURL += '?page='
                         += (param.page - 1)}"/>">
                                <fmt:message key="previousPage" bundle="${lang}"/>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${amountOfPages}">
                        <a class="page-link" href="<c:url value="${paginationURL += '?page=' += i}"/>">
                            <c:out value="${i}"/>
                        </a>
                    </c:forEach>
                    <c:if test="${param.page < amountOfPages}">
                        <li class="page-item">
                            <a class="page-link" href="<c:url value="${paginationURL += '?page='
                         += (param.page + 1)}"/>">
                                <fmt:message key="nextPage" bundle="${lang}"/>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </main>
</div>
<ctgg:footer/>
<%--<jsp:include page="/WEB-INF/jsp/special/footer.jsp" flush="true"/>--%>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
