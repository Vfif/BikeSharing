<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Welcome</title>
    <link href="css/user.css" rel="stylesheet"/>
</head>
<body style="background: url(image/map.png) no-repeat; background-size: 100%;">

<h2>
    <label>
        <fmt:message key="welcome.text"/>, ${role} ${login}!
    </label>
</h2>

</br>
<label><fmt:message key="label.balance"/>: ${cash}$
</label>
</br>
Bike : ${name}
</br>
Cost: ${cost}$</br>
Description: ${description}</br>
Address: ${address}</br>
<img src="bikeImage/${image}" alt="Bike"/>
<form method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="rent"/>
    <input type="submit" value="<fmt:message key="button.rent"/>"/>
</form>
</body>
</html>
