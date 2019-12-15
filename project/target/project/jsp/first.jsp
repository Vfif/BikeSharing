<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="welcome.text"/></title>
    <link  href="${pageContext.request.contextPath}/css/first.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/jsp/login.jsp">
    <input type="submit" value="<fmt:message key="button.logIn"/>"/>
</form>
<form action="${pageContext.request.contextPath}/jsp/registration.jsp">
    <input type="submit" value="<fmt:message key="button.signUp"/>"/>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post" style="float:right;">
    <input type="hidden" name="command" value="locale"/>
    <input type="submit" value="<fmt:message key="button.changeLocale"/>"/>
</form>
<div id="center">
    <div class="content"><h1><fmt:message key="header.text"/></h1></div>
</div>
<img src="${pageContext.request.contextPath}/image/bike.png" alt="Bike" width="100%"/>
</body>

</html>
