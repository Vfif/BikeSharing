<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/load.css" rel="stylesheet"/>
</head>
<body>
    <img src="${pageContext.request.contextPath}/image/spinner.gif" alt="load" class="image-center" style=" "/>
    </br>
    </br>
    </br>
    <div style="text-align:center;">
        hello
    </div>

    <script type="text/javascript">
        setTimeout(function () {'<form action="${pageContext.request.contextPath}/controller" method="post">' +
        '<input type="hidden" name="command" value="logout"/></form>'}, 5000).submit();
    </script>
</body>
</html>
