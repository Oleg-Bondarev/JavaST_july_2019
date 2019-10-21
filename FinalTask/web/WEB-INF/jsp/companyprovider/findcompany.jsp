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
    <title><fmt:message key="companies" bundle="${lang}"/></title>

    <c:url value="/companyprovider/findcompany.html?page=1" var="findCompanyPageActionURL"/>
    <c:url value="/companyprovider/findcompanybyphone.html" var="findByPhoneActionURL"/>
    <c:url value="/companyprovider/findcompanybyname.html?page=1" var="findByNameActionURL"/>
    <c:url value="/companyprovider/companyblocking.html" var="companyBlockingActionURL"/>
    <c:url value="/companyprovider/addcompanypage.html" var="addCompanyProviderActionPageURL"/>
</head>
<body>
<div id="wrap">
    <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
    <main>
        <div class="text-center">
            <h2>
                <fmt:message key="companies" bundle="${lang}"/>
            </h2>
        </div>
        <div class="row">
            <div class="col-md-3 align-content-center">
                <form class="form-inline" method="post" action="${findByPhoneActionURL}">
                    <div class="form-group">
                        <div>
                            <input class="form-control mr-sm-2" name="phoneParameter"
                                   type="search" placeholder="<fmt:message key="phonePlaceholder" bundle="${lang}"/>"
                                   value="${sessionScope.phoneParameter}" required>
                        </div>
                        <div>
                            <button class="btn btn-outline-success" type="submit">
                                <fmt:message key="searchButton" bundle="${lang}"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-3 justify-content-center">
                <form class="form-inline" method="post" action="${findByNameActionURL}">
                    <div class="form-group">
                        <div>
                            <input class="form-control mr-sm-2" name="nameParameter"
                                   type="search" placeholder="<fmt:message key="inputNamePlaceholder" bundle="${lang}"/>"
                                   value="${sessionScope.nameParameter}" required>
                        </div>
                        <div>
                            <button class="btn btn-outline-success" type="submit">
                                <fmt:message key="searchButton" bundle="${lang}"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-3 justify-content-center">
                <form class="col-md-6" action="${findCompanyPageActionURL}" method="post">
                    <button class="btn btn-outline-success" type="submit" >
                        <fmt:message key="showAllCompaniesButton" bundle="${lang}"/>
                    </button>
                </form>
            </div>
            <div class="col-md-3 justify-content-center">
                <form class="col-md-6" action="${addCompanyProviderActionPageURL}" method="post">
                    <button class="btn btn-outline-success" type="submit" >
                        <fmt:message key="addCompanyProvider" bundle="${lang}"/>
                    </button>
                </form>
            </div>
        </div>

        <div class="row">
            <div class="card-body">
                <div class="table-wrap">
                    <table>
                        <thead>
                        <tr style="background-color: #7c6354">
                            <th><fmt:message key="companyName" bundle="${lang}"/></th>
                            <th><fmt:message key="addressTitle" bundle="${lang}"/></th>
                            <th><fmt:message key="phoneTitle" bundle="${lang}"/></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${resultCompanies}" var="comp">
                            <tr>
                                <td><c:out value="${comp.companyName}"/></td>
                                <td><c:out value="${comp.companyAddress}"/></td>
                                <td><c:out value="+375${comp.mobilePhone}"/></td>
                                <td>
                                    <form action="${companyBlockingActionURL}" method="post">
                                        <input type="hidden" name="companyToBlock" value="${comp.id}">
                                        <button class="btn btn-danger" type="submit">
                                            <fmt:message key="blockCompany" bundle="${lang}"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
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

    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
