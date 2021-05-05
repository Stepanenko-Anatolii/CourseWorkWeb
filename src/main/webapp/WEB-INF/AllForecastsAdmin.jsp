<%@ page import="Models.WeatherModel" %>
<%@ page import="Models.DayWeatherModel" %>
<%@ page import="Logic.WeatherProvider" %>
<%@ page import="dal.WeatherDataObject" %>
<%@ page import="dal.WeatherData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Week forecast(admin page)</title>
</head>
<body>

<%
    WeatherDataObject weatherDB = new WeatherData();
    WeatherModel weatherData = weatherDB.getAllWeather();
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
    <%----%>
    <%for(String dayWeatherDate: weatherData.getDaysWeather().keySet()){%>
    <%DayWeatherModel dayWeather = weatherData.getDaysWeather().get(dayWeatherDate);%>
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
<form method="post" action="DispatcherServlet">
    <input type="hidden" name="link" value="AddDayForecastView.jsp">
    <input type="submit" value="Add new forecast">
</form>
<%-- <a href="AddDayForecastView.jsp">Add new forecast</a> --%>
</body>
</html>
