<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.login"/></title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>
</head>
<body>
<form class="form-3" name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">

    <p class="clearfix">
        <label for="login"><fmt:message key="label.login"/></label>
        <input type="text" name="login" id="login"/>
    </p>
    <p class="clearfix">
        <label for="password"><fmt:message key="label.password"/></label>
        <input type="password" name="password" id="password"/>
    </p>
    <p class="clearfix">
        <c:if test="${errorLogOrPass}">
            <fmt:message key="invalid.login.password.text"/>
        </c:if>
    </p>
    <p class="clearfix">
        <input type="submit" value="<fmt:message key="button.logIn"/>"/>
    </p>


    <input type="hidden" name="command" value="login"/>
</form>
</body>
</html>
