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
<h1>A pleasant journey</h1>
<form action="jsp/user/replenishment.jsp">
    <input type="submit" value="<fmt:message key="button.replenishAccount"/>"/>
</form>
</br>
<form method="post" action="${pageContext.request.contextPath}/controller"
      onclick="function check() {

              var elem = document.getElementById('invalid');
              if ((new Date().getTime() - ${rentTime})/(1000 * 60) * ${cost} > ${cash}){
              elem.style.display = 'block';
              }
              else this.submit();}">
    <input type="hidden" name="command" value="pay"/>
    <input type="submit" value="<fmt:message key="button.pay"/>"/>
    <div id = "invalid" style="display:none;"><fmt:message key="button.lack.money"/></div>
</form>
</body>
</html>
