<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.registration"/></title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>

</head>
<body>
<form class="form-3" action="${pageContext.request.contextPath}/controller" method="post">
    <label for="login"><fmt:message key="label.login"/>*:</label>
        <input type="text" name="login" id="login" pattern="[A-Za-zА-Яа-я\d\-\_]{0,45}"
               maxlength="45"
               title="<fmt:message key="invalid.login"/>" required/>

        <c:if test="${incorrectLogin}">
            <label><fmt:message key="invalid.registration.login"/></label>
        </c:if>

    <label for="password"><fmt:message key="label.password"/>*:</label>
        <input type="text" name="password" id="password"
               pattern="(?=.*[A-ZА-Я])(?=.*[a-zа-я])(?=.*\d)([A-Za-zА-Яа-я\d]{8,45})"
               maxlength="45"
               title = "<fmt:message key="invalid.registration.password"/>"
               required/>

    <c:if test="${incorrectPassword}">
        <label><fmt:message key="invalid.registration.password"/></label>
    </c:if>


    <label for="email"><fmt:message key="label.email"/>*:</label>
        <input type="text" name="email" id="email"
               pattern="^[A-Za-z\d\_]{6,}@[a-z]+\.[a-z]{2,4}$"
               maxlength="45"
               title = "<fmt:message key="invalid.registration.email"/>"
               required/>

    <c:if test="${incorrectEmail}">
        <label style="float: right"> <fmt:message key="invalid.registration.email"/></label>
    </c:if>


    <input type="submit" value="<fmt:message key="button.save"/>"/>
    <input type="hidden" name="command" value="registration"/>

</form>
</body>
</html>
