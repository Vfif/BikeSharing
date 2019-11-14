<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html><head><title>Login</title></head>
<body>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login"/>
    <label for="login"><fmt:message key="label.login"/>:
        <input type="text" name="login" id="login">
    </label>
    <br/>
    <label for="password"><fmt:message key="label.password"/>:
        <input type="password" name="password" id="password">
    </label>
    <br/>
    <c:if test="${errorLogOrPass}">
        <fmt:message key="invalid.login.password.text"/>
    </c:if>
    <br/>
    <input type="submit" value="<fmt:message key="button.logIn"/>"/>
</form><hr/>
</body></html>