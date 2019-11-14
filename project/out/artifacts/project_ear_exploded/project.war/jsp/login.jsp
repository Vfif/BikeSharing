<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'ru'}"/>
<fmt:setBundle basename="pagecontent"/>
<html><head><title>Login</title></head>
<body>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login"/>
    <label for="login"><fmt:message key="label.login"/>:
        <input type="text" name="login" id="login">
    </label><br><br>
    <label for="password"><fmt:message key="label.password"/>:
        <input type="password" name="password" id="password">
    </label><br><br>
    <br/>
    <c:if test="${errorLogOrPass}">
        <fmt:message key="invalid.text"/>
    </c:if>
    <br/>
    <input type="submit" value="Log in"/>
</form><hr/>
</body></html>