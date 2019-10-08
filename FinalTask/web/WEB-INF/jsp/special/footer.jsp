<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 06.10.2019
  To change this template use File | Settings | File Templates.
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<%@ taglib prefix = "ex" uri = "/tags/livedate"%>--%>
<fmt:setLocale value="${sessionLang}"/>
<fmt:setBundle basename="by.training.finaltask.resource.localization"/>
<!-- Footer -->
<footer>
    <div class="container">
        <div class="d-flex justify-content-between">
            <div class="footer-left">
                <div class="col-md-6"><fmt:message key="createdBy"/>
                    <a href="https://github.com/Oleg-Bondarev/"><br>
                        Github
                    </a>
                    <a href="https://vk.com/oleg_bondarev/">
                        VK
                    </a>
                </div>
            </div>
            <div class="footer-right">
                <span>Follow us:</span>
                <div class="row right-padding">
                    <div><a href="https://ru-ru.facebook.com/"><i class="fab fa-facebook-f"></i></a></div>
                    <div><a href="https://twitter.com/"><i class="fab fa-twitter"></i></a></div>
                    <div><a href="https://vk.com/oleg_bondarev"><i class="fab fa-vk"></i></a></div>
                </div>
            </div>
        </div>
    </div>
</footer>