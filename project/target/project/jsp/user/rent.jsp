<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet"/>
    <title><fmt:message key="title.rent"/></title>
    <script type="text/javascript">
        window.onload = function () {
            (function () {
                var time = Math.ceil(((new Date().getTime() - ${time}) * ${cost}) / (1000 * 60 * 60))
                    , div = document.getElementsByTagName('div');
                div[0].innerHTML = time;
                div[1].innerHTML = time - ${cash};
                window.setTimeout(arguments.callee, 1000);
            })();
        };
    </script>

</head>
<body style="background: url(${pageContext.request.contextPath}/image/handlebar.jpeg) no-repeat; background-size: 100%;">
<form class="form-3" method="post" action="${pageContext.request.contextPath}/controller">
    <label style="width: 100%"><h1><fmt:message key="label.trip"/></h1></label>

    <label style="width: 100%"><fmt:message key="label.balance"/>: ${cash}$</label>
    <label style="width: 100%"><fmt:message key="label.pay"/>: <div style="display: inline"></div>$</label>

    <input type="submit" value="<fmt:message key="button.replenishAccount"/>"/>
    <input type="hidden" name="command" value="go_to"/>
    <input type="hidden" name="page" value="replenishment"/>
</form>
<form class="form-3" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="pay"/>
    <input type="submit" value="<fmt:message key="button.pay"/>"/>
    <c:if test="${invalid}">
        <fmt:message key="button.lack.money"/>:
        <div style="display: inline"></div>$
    </c:if>
</form>
</body>
</html>
