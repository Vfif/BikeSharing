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

<table style="width: auto">
    <tr>
        <th>Bike</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${bikes}" var="bike">
        <tr>
            <td>${bike.name}</td>
            <td>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="change_bike"/>
                    <input type="hidden" name="bike_id" value="${bike.id}"/>
                    <input type="submit" value="<fmt:message key="button.change"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="go_to"/>
    <input type="hidden" name="page" value="admin"/>
    <input type="hidden" name="delete" value="bikes"/>
    <input type="submit" value="<fmt:message key="button.back"/>"/>
</form>
</body>
</html>
