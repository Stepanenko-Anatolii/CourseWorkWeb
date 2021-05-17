<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Stylesheets/BasicStyle.css" type="text/css">
    <title>Weather</title>
</head>
<body>
<form action="LocationController" method="post">
    <div>Choose location, to look weather there:</div>
    <select name="country" >
        <option value="ukraine">Ukraine</option>
        <option value="russia">Russia</option>
        <option value="spain">Spain</option>
        <option value="italy">Italy</option>
        <option value="vatican">Vatican</option>
    </select>

    <input type="submit" value="Ok">
</form><br>

<a href="Authorization.jsp">Login as admin</a>

</body>
</html>
