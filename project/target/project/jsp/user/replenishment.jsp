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
${role} ${login}, <fmt:message key="welcome.text"/>!
</br>
<label><fmt:message key="label.balance"/>:
    ${cash}$
</label>
</br>
<form method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="replenishment"/>
    <label for="card"><fmt:message key="label.amount"/></label>
    <input type="text" name="money" id="card" pattern="\d{16}"
           oninvalid="setCustomValidity('<fmt:message key="invalid.format.card"/>')"/>
    </br>
    <label for="money"><fmt:message key="label.card"/></label>
    <input type="text" name="money" id="money" pattern="\d{0,5}"
           oninvalid="setCustomValidity('<fmt:message key="invalid.format.money"/>')"/>$
    <input type="submit" value="<fmt:message key="button.replenishAccount"/>"/>
</form>
</body>
</html>
