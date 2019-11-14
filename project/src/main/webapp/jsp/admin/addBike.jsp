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
<form action="${pageContext.request.contextPath}/controller" enctype="multipart/form-data" method="post">
    <input type="hidden" name="command" value="add_bike"/>
    <label for="name"><fmt:message key="label.name"/>:
        <input type="text" name="name" id="name" pattern="[A-Za-zА-Яа-я\d]+"
               title="<fmt:message key="invalid.name"/>" required/>
    </label>
    <br/>
    <label for="cost"><fmt:message key="label.cost"/>:
        <input type="text" name="cost" id="cost"
               pattern="\d+"
               maxlength="5"
               title="<fmt:message key="invalid.cost"/>"
               required/>
    </label>
    <br/>
    <label for="address"><fmt:message key="label.address"/>:
        <input type="text" name="address" id="address"
               pattern="[A-Za-zА-Яа-я\d]+"
               title="<fmt:message key="invalid.address"/>"
               required/>
    </label>
    <br/>
    <label for="description"><fmt:message key="label.description"/>:
        <input type="text" name="description" id="description"
               pattern="[A-Za-zА-Яа-я\d]+"
               title="<fmt:message key="invalid.description"/>"
               required/>
    </label>
    <br/>
    <input type="file" name="image"
           accept="image/jpeg,image/png" required/>
    </br>
    <input type="submit" value="<fmt:message key="button.save"/>"/>
</form>
</body>
</html>
