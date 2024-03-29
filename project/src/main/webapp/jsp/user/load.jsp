<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.load"/></title>
    <link href="${pageContext.request.contextPath}/css/load.css" rel="stylesheet"/>
</head>
<body>
    <img src="${pageContext.request.contextPath}/image/spinner.gif" alt="load" class="image-center"/>
    </br>
    </br>
    </br>
    <h2 style="text-align:center;">
        <fmt:message key="text.search"/>
    </h2>

    <script type="text/javascript">
        setTimeout(function () {
            let form = document.createElement('form');
            form.action = '${pageContext.request.contextPath}/controller';
            form.method = 'post';
            form.innerHTML = '<input type="hidden" name="command" value="positioning"/>';
            document.body.append(form);
            form.submit();
        }, 5000);
    </script>
</body>
</html>
