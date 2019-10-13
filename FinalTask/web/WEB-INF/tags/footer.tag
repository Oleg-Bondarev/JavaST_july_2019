
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionLang}"/>
<fmt:setBundle basename="by.training.final_task.resource.localization"/>
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
                    <div><a href="https://ru-ru.facebook.com/"><em class="fab fa-facebook-f"></em></a></div>
                    <div><a href="https://twitter.com/"><em class="fab fa-twitter"></em></a></div>
                    <div><a href="https://vk.com/oleg_bondarev"><em class="fab fa-vk"></em></a></div>
                </div>
            </div>
        </div>
    </div>
</footer>