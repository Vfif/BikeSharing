<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="button.changeBike"/></title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>

</head>
<body>
<form class="form-3" action="${pageContext.request.contextPath}/controller" method="post" style="margin-top: 20px">
    <input type="submit" value="<fmt:message key="button.back"/>"/>
    <input type="hidden" name="command" value="go_to"/>
    <input type="hidden" name="page" value="changeBike"/>
    <input type="hidden" name="delete" value="bike"/>
</form>

<form class="form-3" action="${pageContext.request.contextPath}/controller" method="post" style="margin-top: 10px">
    <label for="name"><fmt:message key="label.name"/>:</label>
    <input type="text" name="value" id="name" pattern="[A-Za-zА-Яа-я\d\s\-_]{1,45}"
           placeholder="${bike.name}"
           maxlength="45"
           title="<fmt:message key="invalid.name"/>" required/>
    <c:if test="${incorrectName}">
        <fmt:message key="invalid.name"/>
    </c:if>
    <input type="submit" value="<fmt:message key="button.save"/>"/>
    <input type="hidden" name="command" value="modify_bike"/>
    <input type="hidden" name="field" value="name"/>

</form>

<form class="form-3" action="${pageContext.request.contextPath}/controller" method="post" style="margin-top: 10px">
    <label for="cost"><fmt:message key="label.cost"/>: </label>
    <input type="text" name="value" id="cost"
           placeholder="${bike.cost}"
           pattern="\d{1,3}"
           maxlength="3"
           title="<fmt:message key="invalid.cost"/>" required/>

    <c:if test="${incorrectCost}">
        <fmt:message key="invalid.cost"/>
    </c:if>
    <input type="submit" value="<fmt:message key="button.save"/>"/>
    <input type="hidden" name="command" value="modify_bike"/>
    <input type="hidden" name="field" value="cost"/>

</form>
<form class="form-3" action="${pageContext.request.contextPath}/controller" method="post" style="margin-top: 10px">
<label for="address"><fmt:message key="label.address"/>:</label>
    <input type="text" name="value" id="address"
           placeholder="${bike.address}"
           pattern="[A-Za-zА-Яа-я\d\s\.,]{1,45}"
           maxlength="45"
           title="<fmt:message key="invalid.address"/>" required/>

    <c:if test="${incorrectAddress}">
        <fmt:message key="invalid.address"/>
    </c:if>
    <input type="submit" value="<fmt:message key="button.save"/>"/>
    <input type="hidden" name="command" value="modify_bike"/>
    <input type="hidden" name="field" value="address"/>
</form>
<form class="form-3" action="${pageContext.request.contextPath}/controller" method="post" style="margin-top: 10px">
    <label for="description"><fmt:message key="label.description"/>:</label>
    <input type="text" name="value" id="description"
           placeholder="${bike.description}"
           pattern="[A-Za-zА-Яа-я\d\s.,]{1,200}"
           maxlength="200"
           title="<fmt:message key="invalid.description"/>" required/>

    <c:if test="${incorrectDescription}">
        <fmt:message key="invalid.description"/>
    </c:if>

    <input type="submit" value="<fmt:message key="button.save"/>"/>
    <input type="hidden" name="command" value="modify_bike"/>
    <input type="hidden" name="field" value="description"/>
</form>
</body>
</html>
