<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Stylesheets/BasicStyle.css" type="text/css">
    <title>Add day forecast</title>
</head>
<body>
<h3>Make forecast for a new day</h3>

<form action="add-day-forecast" method="post">
    Country:
    <select name="country" >
        <option value="ukraine">Ukraine</option>
        <option value="russia">Russia</option>
        <option value="spain">Spain</option>
        <option value="italy">Italy</option>
        <option value="vatican">Vatican</option>
    </select><br>
    Date: <input type="date" name="date"><br>
    Temperature: <input type="text" name="temperature"><br>
    Humidity: <input type="text" name="humidity"><br>
    Wind speed: <input type="text" name="windSpeed"><br>
    Atmosphere pressure: <input type="text" name="atmospherePressure"><br>
    Rain chance: <input type="text" name="rainChance"><br>
    Rainfall: <input type="text" name="rainfall"><br>
    Cloudiness: <input type="text" name="cloudiness"><br>

    <input type="submit" value="Ok">
</form>

</body>
</html>
