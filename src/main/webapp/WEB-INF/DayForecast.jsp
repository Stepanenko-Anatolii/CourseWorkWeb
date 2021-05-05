<%@ page import="Models.DayWeatherModel" %>
<%@ page import="Logic.WeatherProvider" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%DayWeatherModel dayWeather = (DayWeatherModel) request.getAttribute("dayWeather");%>
<%String demonstrationDate = WeatherProvider.getDemonstrationDateString(dayWeather.getCalendar());%>
<head>
    <title><%=demonstrationDate%> forecast</title>
</head>
<body>

<h3>Weather forecast for <%=demonstrationDate%></h3>

<b>Location: </b><%=dayWeather.getLocation().getCountry()%><br>
<b>Day of week: </b><%=WeatherProvider.getWeekDay(dayWeather.getCalendar())%><br>
<b>Date: </b><%=demonstrationDate%><br>
<b>Temperature: </b><%=dayWeather.getTemperature()%><br>
<b>Cloudiness: </b><%=dayWeather.getCloudiness()%><br>
<b>Rain chance: </b><%=dayWeather.getRainChance()%><br>
<b>Humidity: </b><%=dayWeather.getHumidity()%><br>
<b>Wind speed: </b><%=dayWeather.getWindSpeed()%><br>
<b>Atmosphere pressure: </b><%=dayWeather.getAtmospherePressure()%><br>
<b>Rainfall: </b><%=dayWeather.getRainfall()%><br>

</body>
</html>
