<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/banUser.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet"/>
</head>
<body>

<table>
    <tr>
        <th>Login</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="save_status"/>
            <c:choose>
                <c:when test="${user.status}">
                    <fmt:message key="button.unblock" var="unblock"/>
                    <ctg:row-with-button login="${user.login}" status="${user.status}" value="${unblock}"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="button.block" var="block"/>
                    <ctg:row-with-button login="${user.login}" status="${user.status}" value="${block}"/>
                </c:otherwise>
            </c:choose>
        </form>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="go_to"/>
    <input type="hidden" name="page" value="admin"/>
    <input type="submit" value="<fmt:message key="button.back"/>"/>
</form>
</body>
</html>
