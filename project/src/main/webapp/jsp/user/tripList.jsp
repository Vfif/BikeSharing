<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="button.history"/></title>
    <link href="${pageContext.request.contextPath}/css/banUser.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet"/>
</head>
<body>

<table style="width: auto">
    <tr>
        <th><fmt:message key="label.bike"/></th>
        <th><fmt:message key="label.date"/></th>
        <th><fmt:message key="label.payment"/>($)</th>
        <th><fmt:message key="label.mark"/></th>
    </tr>
    <c:forEach items="${trips}" var="trip">
    <tr>
        <td>${trip.bikeName}</td>
        <td>${trip.time}</td>
        <td>${trip.money}</td>
        <c:choose>
        <c:when test="${trip.mark != 0}">
        <td>${trip.mark}</td>
        </c:when>
        <c:otherwise>
        <td> - </td>
        </c:otherwise>
        </c:choose>
    </tr>
        </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="go_to"/>
    <input type="hidden" name="page" value="end"/>
    <input type="hidden" name="delete" value="trips"/>
    <input type="submit" value="<fmt:message key="button.back"/>"/>
</form>
</body>
</html>
