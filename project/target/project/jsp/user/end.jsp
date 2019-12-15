<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/rate.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>

</head>
<body style="background: url(image/city.jpg) no-repeat; background-size: 100%;">
<form class="form-3" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="submit" value="<fmt:message key="button.history"/>"/>
    <input type="hidden" name="command" value="history"/>
</form>
<form class="form-3" method="POST" action="${pageContext.request.contextPath}/controller">
    <c:choose>
        <c:when test="${invalid}">
            <label style=" width:100%;"><fmt:message key="text.oops"/></label>
        </c:when>
        <c:otherwise>
            <label style=" width: 100%;text-align: center;"><fmt:message key="text.end"/></label>

            <p class="clearfix">
            <label style=" width: 100%; text-align: center;"><fmt:message key="text.rate"/></label>
            </p>
            <div id="rate-input">
                <input id="star-4" type="radio" name="rate" value="5"/>
                <label title="gorgeous" for="star-4"></label>

                <input id="star-3" type="radio" name="rate" value="4"/>
                <label title="good" for="star-3"></label>

                <input id="star-2" type="radio" name="rate" value="3"/>
                <label title="regular" for="star-2"></label>

                <input id="star-1" type="radio" name="rate" value="2"/>
                <label title="poor" for="star-1"></label>

                <input id="star-0" type="radio" name="rate" value="1"/>
                <label title="bad" for="star-0"></label>
            </div>

        </c:otherwise>
    </c:choose>
    <p class="clearfix"></p>
    <p class="clearfix"></p>

    <p class="clearfix"></p>
    <input type="submit" value="<fmt:message key="button.logout"/>"/>
    <input type="hidden" name="command" value="rate"/>
</form>
</body>
</html>
