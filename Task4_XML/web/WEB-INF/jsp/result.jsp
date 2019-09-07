<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 07.09.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Result Page</title>
</head>
<body>
    <div>
        <h1>Results using ${parser}</h1><br>
    </div>
    <div>
        <h2>Wild flowers</h2>
        <table border="5px" align="center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Flower name</th>
                    <th>Medical</th>
                    <th>Multiplying</th>
                    <th>Soil</th>
                    <th>Steam color</th>
                    <th>Leaf color</th>
                    <th>Average size</th>
                    <th>Temperature</th>
                    <th>Watering</th>
                    <th>Discovery year</th>
                    <th>Protected</th>
                </tr>
            </thead>
            <c:forEach var="flower" items="${wild}">
                <tbody>
                    <tr>
                        <td><c:out value="${flower.identificator}"/></td>
                        <td><c:out value="${flower.flowerName}"/></td>
                        <td><c:out value="${flower.isMedical}"/></td>
                        <td><c:out value="${flower.multiplying}"/></td>
                        <td><c:out value="${flower.soil}"/></td>
                        <td><c:out value="${flower.steamColor}"/></td>
                        <td><c:out value="${flower.leafColor}"/></td>
                        <td><c:out value="${flower.avgSize}"/></td>
                        <td><c:out value="${flower.temperature}"/></td>
                        <td><c:out value="${flower.watering}"/></td>
                        <td><c:out value="${flower.lighting}"/></td>
                        <td><c:out value="${flower.discoveryYear}"/></td>
                        <td><c:out value="${flower.isProtected}"/></td>
                    </tr>
                </tbody>
            </c:forEach>
        </table>
        <br>
    </div>
    <div>
        <h2>Artificial flowers</h2>
        <table border="5px" align="center">
            <thead>
            <tr>
                <th>ID</th>
                <th>Flower name</th>
                <th>Medical</th>
                <th>Multiplying</th>
                <th>Soil</th>
                <th>Steam color</th>
                <th>Leaf color</th>
                <th>Average size</th>
                <th>Temperature</th>
                <th>Watering</th>
                <th>Discovery year</th>
                <th>Scientists names</th>
            </tr>
            </thead>
            <c:forEach var="flower" items="${wild}">
                <tbody>
                <tr>
                    <td><c:out value="${flower.identificator}"/></td>
                    <td><c:out value="${flower.flowerName}"/></td>
                    <td><c:out value="${flower.isMedical}"/></td>
                    <td><c:out value="${flower.multiplying}"/></td>
                    <td><c:out value="${flower.soil}"/></td>
                    <td><c:out value="${flower.steamColor}"/></td>
                    <td><c:out value="${flower.leafColor}"/></td>
                    <td><c:out value="${flower.avgSize}"/></td>
                    <td><c:out value="${flower.temperature}"/></td>
                    <td><c:out value="${flower.watering}"/></td>
                    <td><c:out value="${flower.lighting}"/></td>
                    <td><c:out value="${flower.discoveryYear}"/></td>
                    <td>
                        <c:forEach var="scientists" items="${flower.scientistsNames}">
                            <c:out value="${scientists}"/>
                        </c:forEach>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <br>
    </div>
</body>
</html>
