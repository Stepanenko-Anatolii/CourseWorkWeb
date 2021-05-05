<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather</title>
</head>
<body>
<form action="LocationController" method="post">
    Choose location, to look weather there:<br>
    <select name="country" >
        <option value="ukraine">Ukraine</option>
        <option value="russia">Russia</option>
        <option value="spain">Spain</option>
        <option value="italy">Italy</option>
        <option value="vatican">Vatican</option>
    </select><br>

    <input type="submit" value="Ok">
</form><br>

<a href="Authorization.jsp">Login as admin</a>

</body>
</html>
