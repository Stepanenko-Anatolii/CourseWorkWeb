<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Stylesheets/BasicStyle.css" type="text/css">
    <title>Authorization</title>
</head>
<body>
<%if( (request.getAttribute("falseData")!=null) && (boolean)request.getAttribute("falseData") ) {%>
<script type='text/javascript'>
alert("Wrong login or password. Please try again")
</script>
<%}%>
<form action="AuthorizationController" method="post">
    <div>Login: </div><input type="text" name="login"><br>
    <div>Password: </div><input type="password" name="password"><br>

    <input type="submit" value="Ok">
</form><br>
</body>
</html>
