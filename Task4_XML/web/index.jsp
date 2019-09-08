<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 07.09.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>WebParserForFlowers</title>
    <style>
        body{background-color: green;}
        h1{
            color: black;
            font-family: "Arial Narrow";
        }
        h4{
            color: black;
            font-family: "Arial Narrow";
        }
    </style>
</head>
<body>
    <div align="center">
        <h1 align="center">Parsing XML document.</h1>
        <form name="XML WebParser" method="get" action="parsing" enctype="multipart/form-data">
            <input type="radio" name="parserType" value="DOM" checked>DOM parser<br>
            <input type="radio" name="parserType" value="SAX">SAX parser<br>
            <input type="radio" name="parserType" value="STAX">StAX parser<br>
            <input type="text" name="xmlText"/>

            <input type="submit" value="Parse"/>
        </form>
    </div>
</body>
</html>
