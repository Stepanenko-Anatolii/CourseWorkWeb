<%@ page import="Models.WeatherModel" %>
<%@ page import="Models.DayWeatherModel" %>
<%@ page import="Logic.WeatherProvider" %>
<%@ page import="dal.WeatherData" %>
<%@ page import="dal.WeatherDataObject" %>
<%@ page import="Models.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Week forecast</title>
</head>
<body>

<%
    Location location = new Location( (String) request.getAttribute("location"));
    WeatherDataObject weatherDB = new WeatherData();
    WeatherModel weatherData = weatherDB.getAllWeatherByLocation(location);
%>
<table border="2">
    <tr>
        <th>Day of week</th>
        <th>Date</th>
        <th>Temperature</th>
        <th>cloudiness</th>
        <th>rain chance</th>
        <th>humidity</th>
    </tr>

    <%for(String dayWeatherDate: weatherData.getDaysWeatherWeek().keySet()){%>
    <%DayWeatherModel dayWeather = weatherData.getDaysWeatherWeek().get(dayWeatherDate);%>
    <%String demonstrationDate = WeatherProvider.getDemonstrationDateString(dayWeather.getCalendar());%>
    <tr>
        <td><%=WeatherProvider.getWeekDay(dayWeather.getCalendar())%></td>
        <td><a href="DayForecastDispatcher?DateString=<%=dayWeatherDate%>"><%=demonstrationDate%></a></td>
        <td><%=dayWeather.getTemperature()%></td>
        <td><%=dayWeather.getCloudiness()%></td>
        <td><%=dayWeather.getRainChance()%></td>
        <td><%=dayWeather.getHumidity()%></td>
    </tr>
    <%}%>
</table><br>
</body>
</html>
