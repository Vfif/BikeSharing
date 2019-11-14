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
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="save_status"/>
        <ctg:table-ban rows="${users.size}" head="Login">
            ${users.login}
        </ctg:table-ban>
        <table style="float:left;">
            <tr><th>Action</th></tr>
            <c:forEach items="${users}" var="user">
                <tr><td>
                        <input type="hidden" name="${user.login}" value="${user.status}"/>
                        <input type="button" name="button"
                               onclick="${user.status} = this.value!=='Block'"
                               value="
                    <c:choose>
                         <c:when test="${user.status}">
                             Block
                         </c:when>
                         <c:otherwise>
                             Unblock
                         </c:otherwise>
                    </c:choose>"/>
                    </td></tr>
            </c:forEach>
        </table>
        <input type="submit" value="<fmt:message key="button.save"/>"/>
    </form>
</div>
</body>
</html>
