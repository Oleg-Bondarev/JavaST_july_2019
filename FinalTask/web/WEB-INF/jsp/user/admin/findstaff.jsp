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
    <title><fmt:message key="findStaffTitle" bundle="${lang}"/></title>

    <c:url value="/user/userblocking.html" var="userBlockingActionURL"/>
    <c:url value="/user/admin/findstaffbyfirstname.html?page=1" var="findStaffByFirstNameAction"/>
    <c:url value="/user/admin/findstaff.html?page=1" var="findStaffAction"/>
    <c:url value="/user/admin/addstaffpage.html" var="addstaffActionPageURL"/>
</head>
<body>
    <div id="wrap">
        <jsp:include page="/WEB-INF/jsp/special/header.jsp" flush="true"/>
        <main>
            <div class="text-center">
                <h2>
                    <fmt:message key="findStaff" bundle="${lang}"/>
                </h2>
            </div>
            <div class="row">
                <div class="col-md-4 justify-content-center">
                    <form class="form-inline" method="post" action="${findStaffByFirstNameAction}">
                        <div class="form-group">
                            <div>
                                <input class="form-control mr-sm-2" name="firstNameParameter"
                                       type="search" placeholder="<fmt:message key="inputNamePlaceholder"  bundle="${lang}"/>"
                                       value="${sessionScope.firstNameParameter}" required>
                            </div>
                            <div>
                                <input class="form-control mr-sm-2" name="secondNameParameter"
                                       type="search" placeholder="<fmt:message key="inputSecondNamePlaceholder"  bundle="${lang}"/>"
                                       value="${sessionScope.secondNameParameter}">
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
                    <form class="col-md-6" action="${findStaffAction}" method="post">
                        <button class="btn btn-outline-success" type="submit" >
                            <fmt:message key="showAllStaffButton" bundle="${lang}"/>
                        </button>
                    </form>
                </div>
                <div class="col-md-4 justify-content-center">
                    <form class="col-md-6" action="${addstaffActionPageURL}" method="post">
                        <button class="btn btn-outline-success" type="submit" >
                            <fmt:message key="addStaff" bundle="${lang}"/>
                        </button>
                    </form>
                </div>
            </div>

            <c:if test="${not empty message}">
                <div class="text-center">
                    <fmt:message key="${message}" bundle="${lang}"/>
                </div>
            </c:if>

            <div class="row align-content-center">
                <div>
                    <div class="card-body">
                        <div class="table-wrap">
                            <table>
                                <thead>
                                <tr style="background-color: #7c6354">
                                    <th><fmt:message key="username" bundle="${lang}"/> </th>
                                    <th><fmt:message key="firstName" bundle="${lang}"/> </th>
                                    <th><fmt:message key="lastName" bundle="${lang}"/> </th>
                                    <th><fmt:message key="email" bundle="${lang}"/> </th>
                                    <th><fmt:message key="phoneTitle" bundle="${lang}"/> </th>
                                    <th><fmt:message key="registrationDate" bundle="${lang}"/> </th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${resultUsers}" var="staff">
                                    <tr>
                                        <td><c:out value="${staff.login}"/></td>
                                        <td><c:out value="${staff.firstName}"/></td>
                                        <td><c:out value="${staff.secondName}"/></td>
                                        <td><c:out value="${staff.email}"/></td>
                                        <td><c:out value="+375${staff.mobilePhone}"/></td>
                                        <td><c:out value="${staff.registrationDate}"/></td>
                                        <td>
                                            <form action="${userBlockingActionURL}" method="post">
                                                <input type="hidden" name="userToBlock" value="${staff.id}">
                                                <button class="btn btn-danger" type="submit">
                                                    <fmt:message key="blockProfile" bundle="${lang}"/>
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
