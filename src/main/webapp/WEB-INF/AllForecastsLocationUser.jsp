<%@ page import="Models.DayWeatherModel" %>
<%@ page import="Logic.WeatherProvider" %>
<%@ page import="Models.Location" %>
<%@ page import="Models.WeatherPoint" %>
<%@ page import="java.util.TreeMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All forecasts</title>
</head>
<body>

<%
    TreeMap<WeatherPoint, DayWeatherModel> allDaysWeather = (TreeMap<WeatherPoint, DayWeatherModel>) request.getAttribute("allDaysWeather");
    Location location = allDaysWeather.firstEntry().getValue().getLocation();
    String countryFirstUpper = Character.toUpperCase(location.getCountry().charAt(0)) + location.getCountry().substring(1);
%>
<h4>All forecasts for <%=countryFirstUpper%></h4>
<table border="2">
    <tr>
        <th>Day of week</th>
        <th>Date</th>
        <th>Temperature</th>
        <th>cloudiness</th>
        <th>rain chance</th>
        <th>humidity</th>
    </tr>

    <%for(WeatherPoint dayWeatherDate: allDaysWeather.keySet()){%>
    <%DayWeatherModel dayWeather = allDaysWeather.get(dayWeatherDate);%>
    <%String demonstrationDate = WeatherProvider.getDemonstrationDateString(dayWeather.getCalendar());%>
    <%String dateString = WeatherProvider.getDateString(dayWeatherDate.getCalendar());%>
    <tr>
        <td><%=WeatherProvider.getWeekDay(dayWeather.getCalendar())%></td>
        <td><a href="DayForecastDispatcher?DateString=<%=dateString%>&Location=<%=location.getCountry()%>"><%=demonstrationDate%></a></td>
        <td><%=dayWeather.getTemperature()%></td>
        <td><%=dayWeather.getCloudiness()%></td>
        <td><%=dayWeather.getRainChance()%></td>
        <td><%=dayWeather.getHumidity()%></td>
    </tr>
    <%}%>
</table><br>
</body>
</html>
