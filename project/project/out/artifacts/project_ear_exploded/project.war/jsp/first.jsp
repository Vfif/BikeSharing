<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty locale ? locale : 'en'}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="welcome.text"/></title>
    <link rel="stylesheet" href="css/first.css"/>
    <link href="css/button.css" rel="stylesheet"/>
</head>
<body>
<form action="jsp/login.jsp">
    <input type="submit" value="<fmt:message key="button.logIn"/>"/>
</form>
<form action="jsp/registration.jsp">
    <input type="submit" value="<fmt:message key="button.signUp"/>"/>
</form>
<form name="localeForm" method="POST" action="controller" style="float:right;">
    <input type="hidden" name="command" value="locale"/>
    <input type="submit" value="<fmt:message key="button.changeLocale"/>"
           name="locale"/>
</form>
<div id="center">
    <div class="content"><h1><fmt:message key="header.text"/></h1></div>
</div>
<img src="image/bike.png" alt="Bike" width="100%"/>
</body>

</html>
