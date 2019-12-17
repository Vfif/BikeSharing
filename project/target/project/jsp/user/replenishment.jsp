<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.replenishment"/></title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>
</head>
<body>
<form class="form-3" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="go_to"/>
    <input type="hidden" name="page" value="rent"/>
    <input type="submit" value="<fmt:message key="button.back"/>"/>
</form>
<form class="form-3">
<label style="width: 100%"><fmt:message key="welcome.text"/>, ${login}!</label>
<label style="width: 100%"><fmt:message key="label.balance"/>: ${cash}$</label>
</form>
<form class="form-3" method="POST" action="${pageContext.request.contextPath}/controller">

    <label for="card"><fmt:message key="label.card"/></label>
    <input type="text" name="number" id="card" pattern="\d{16}"
           minlength="16" maxlength="16"
           title="<fmt:message key="invalid.format.card"/>" required/>
    <p class="clearfix"></p>
    <label for="money"><fmt:message key="label.amount"/></label>
    <input type="text" name="cash" id="money" pattern="\d{1,5}"
           maxlength="5"
           title="<fmt:message key="invalid.format.money"/>" required/>
    <c:if test="${invalidCard}">
        <fmt:message key="invalid.insufficient.funds"/>
    </c:if>
    <input type="submit" value="<fmt:message key="button.replenishAccount"/>"/>
    <input type="hidden" name="command" value="replenishment"/>
</form>
</body>
</html>
