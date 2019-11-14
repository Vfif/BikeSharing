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
<body <%--style="background: url(image/map.png) no-repeat; background-size: 100%;"--%>>

    <h2>
        <label>
        ${role} ${login}, <fmt:message key="welcome.text"/>!
        </label>
    </h2>

</br>
<label><fmt:message key="label.balance"/>:
    ${cash}
</label>
</br>
<form action="jsp/replenishment.jsp">
    <input type="submit" value="<fmt:message key="button.replenishAccount"/>"/>
</form>
</br>
<select>
    <option>Пункт 1</option>
    <option>Пункт 2</option>
    <option>Пункт 2</option>
    <option>Пункт 2</option>
</select>
</br>
<form action="jsp/replenishment.jsp">
    <input type="submit" value="Оплатить"/>
</form>

</body>
</html>
