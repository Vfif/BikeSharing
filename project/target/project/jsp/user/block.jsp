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
<fmt:message key="text.lock.account"/>
</br>
<a href="controller?command=logout"><fmt:message key="button.logout"/></a>
</body>
</html>
