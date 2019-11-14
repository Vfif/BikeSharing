<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${invalid}">
        <fmt:message key="text.oops"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="text.end"/>
    </c:otherwise>
</c:choose>
</br>
<form action="jsp/user/load.jsp">
    <input type="submit" value="<fmt:message key="button.repeat"/>"/>
</form>
</body>
</html>
