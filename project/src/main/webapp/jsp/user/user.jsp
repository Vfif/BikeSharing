<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Welcome</title>
    <link href="css/user.css" rel="stylesheet"/>
    <link href="css/button.css" rel="stylesheet"/>

</head>
<body style="background: url(image/map.png) no-repeat; background-size: 100%;">
<img src="${image}" alt="Bike" style="background: transparent; float:right; width: 1000px; height: 100%"/>

<h1>
    <label>
        <fmt:message key="welcome.text"/>, ${login}!
    </label>
</h1>

</br>
<h2>
    <div id="rectangle" >
        <fmt:message key="label.name"/> : ${name}
        </br>
        <fmt:message key="label.cost"/>: ${cost}$</br>
        <fmt:message key="label.description"/>: ${description}</br>
        <fmt:message key="label.address"/>: ${address}</br>
    </div>
</h2>
<form method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="rent"/>
    <input type="submit" value="<fmt:message key="button.rent"/>"/>
</form>
<form method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="positioning"/>
    <input type="submit" value="<fmt:message key="button.try"/>"/>
</form>
</body>
</html>
