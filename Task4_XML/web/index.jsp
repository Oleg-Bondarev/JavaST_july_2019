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
        body{background-color: aquamarine;}
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
    <h1 align="center">Parsing XML document.</h1>
    <form name="XML WebParser" method="post" action="parse">
        <input type="radio" name="parserType" value="DOM" checked required>DOM parser<br>
        <input type="radio" name="parserType" value="SAX" required>SAX parser<br>
        <input type="radio" name="parserType" value="STAX" required>StAX parser<br>
        <textarea name="xmlText" rows="10" cols="30">xml file in tomcat bin folder</textarea><br>
    </form>
</body>
</html>
