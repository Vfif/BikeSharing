<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="registrationForm" method="POST" action="controller">
   <%-- <div class="your-class">--%>
        <label for="First_Name">First Name:</label>
        <input name="first_name" id="First_Name" type="text"/>
        <label for="Last_Name">Last Name:</label>
        <input name="last_name" id="Last_Name" type="text"/>
    <%--</div>--%>
    <label for="Email">Email:</label>
    <input name="email" id="Email" type="email"/>

    <label for="Telephone">Telephone:</label>
    <input name="telephone" id="Telephone" type="tel"/>

    <label for="Wedding">Wedding Date:</label>
    <input name="wedding" id="Wedding" type="date"/>

    <label for="Requirements">Specific Requirements:</label>
    <textarea name="requirements" id="Requirements" maxlength="1000" cols="25" rows="6"> </textarea>

    <input type="submit" value="Submit"/>
</form>
</body>
</html>
