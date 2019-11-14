<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/banUser.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div style="float:left;">
    <ctg:table-ban rows="${list.size}" head="Login">
        ${list.login}
    </ctg:table-ban>
    <table style="float:left;">
        <tr><th>Action</th></tr>
        <c:forEach items="${list}" var="user">
            <tr><td>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="ban"/>
                    <input type="hidden" name="login" value="${user.login}"/>
                    <input type="submit" name="button"
                            value="
                    <c:choose>
                         <c:when test="${user.status}">
                             Block
                         </c:when>
                         <c:otherwise>
                             Unblock
                         </c:otherwise>
                    </c:choose>"/>
                </form>
                </td></tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="save_status"/>
    <input type="submit" value="<fmt:message key="button.save"/>"/>
    </form>
</div>
</body>
</html>
