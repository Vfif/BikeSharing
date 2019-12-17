<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head><title>Error</title></head>
<link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet"/>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}
<br/>
${error}
<br/>
<form  method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="<fmt:message key="button.logout"/>"/>
</form>

</body>
</html>