<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="registration"/>
    <label for="login"><fmt:message key="label.login"/>:
        <input type="text" name="login" id="login">
    </label>
    <c:if test="${incorrectLogin}">
        <fmt:message key="invalid.registration.login"/>
    </c:if>
    <br/>
    <label for="password"><fmt:message key="label.password"/>:
        <input type="text" name="password" id="password">
    </label>
    <c:if test="${incorrectPassword}">
        <fmt:message key="invalid.registration.password"/>
    </c:if>
    <br/>
    <label for="email"><fmt:message key="label.email"/>:
        <input type="text" name="email" id="email">
    </label>
    <c:if test="${incorrectEmail}">
        <fmt:message key="invalid.registration.email"/>
    </c:if>
    <br/>
    <input type="submit" value="<fmt:message key="button.save"/>"/>
</form>
</body>
</html>
