<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/input_file.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet"/>

</head>
<body>
<form action="${pageContext.request.contextPath}/jsp/admin/admin.jsp">
    <input type="submit" value="<fmt:message key="button.back"/>"/>
</form>
<form class="form-3" action="${pageContext.request.contextPath}/controller" enctype="multipart/form-data" method="post">
    <label for="name"><fmt:message key="label.name"/>:</label>
        <input type="text" name="name" id="name" pattern="[A-Za-zА-Яа-я\d\s\-_]{1,45}"
               maxlength="45"
               title="<fmt:message key="invalid.name"/>" required/>
    <c:if test="${incorrectName}">
        <fmt:message key="invalid.name"/>
    </c:if>
    <label for="cost"><fmt:message key="label.cost"/>: </label>
        <input type="text" name="cost" id="cost"
               pattern="\d{1,3}"
               maxlength="3"
               title="<fmt:message key="invalid.cost"/>"
               required/>

    <c:if test="${incorrectCost}">
        <fmt:message key="invalid.cost"/>
    </c:if>
    <label for="address"><fmt:message key="label.address"/>:</label>
        <input type="text" name="address" id="address"
               pattern="[A-Za-zА-Яа-я\d\s\.,]{1,45}"
               maxlength="45"
               title="<fmt:message key="invalid.address"/>"
               required/>

    <c:if test="${incorrectAddress}">
        <fmt:message key="invalid.address"/>
    </c:if>
    <label for="description"><fmt:message key="label.description"/>:</label>
        <input type="text" name="description" id="description"
               pattern="[A-Za-zА-Яа-я\d\s.,]{1,200}"
               maxlength="200"
               title="<fmt:message key="invalid.description"/>"
               required/>

    <c:if test="${incorrectDescription}">
        <fmt:message key="invalid.description"/>
    </c:if>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <div class="example-1">
        <div class="form-group">
            <label class="label">
                <i class="material-icons">attach_file</i>
                <span class="title"><fmt:message key="label.image"/></span>
                <input type="file" name="image" accept="image/jpeg,image/png" required/>
            </label>
        </div>
    </div>
    <c:if test="${incorrectImage}">
        <fmt:message key="invalid.image"/>
    </c:if>
    <input type="submit" value="<fmt:message key="button.save"/>"/>
    <input type="hidden" name="command" value="add_bike"/>

</form>
</body>
</html>
