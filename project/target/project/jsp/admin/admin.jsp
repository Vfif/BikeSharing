<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/jsp/admin/addBike.jsp">
    <input type="submit" value="<fmt:message key="button.addBike"/>" style="width: auto"/>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="get_user_list"/>
    <input type="submit" value="<fmt:message key="button.banUser"/>" style="width: auto"/>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="get_bike_list"/>
    <input type="hidden" name="page" value="deleteBike"/>
    <input type="submit" value="<fmt:message key="button.deleteBike"/>" style="width: auto"/>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="get_bike_list"/>
    <input type="hidden" name="page" value="changeBike"/>
    <input type="submit" value="<fmt:message key="button.changeBike"/>" style="width: auto"/>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="<fmt:message key="button.logout"/>"/>
</form>
</body>
</html>
