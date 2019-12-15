<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>
</head>
<body>
<form class="form-3" name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <label style="width: 100%;"><fmt:message key="text.apologize"/></label>
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="<fmt:message key="button.logout"/>"/>
</form>
</body>
</html>
