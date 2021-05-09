<%@ page import="Models.DayWeatherModel" %>
<%@ page import="Logic.WeatherProvider" %>
<%@ page import="Models.WeatherPoint" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="Models.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Week forecast(admin page)</title>
</head>
<body>

<%
    TreeMap<WeatherPoint, DayWeatherModel> allDaysWeather = (TreeMap<WeatherPoint, DayWeatherModel>) request.getAttribute("allDaysWeather");

%>
<table border="2">
    <tr>
        <th>Country</th>
        <th>Date</th>
        <th>Day of week</th>
        <th>Temperature</th>
        <th>cloudiness</th>
        <th>rain chance</th>
    </tr>

    <%for(WeatherPoint dayWeatherDate: allDaysWeather.keySet()){%>
    <%DayWeatherModel dayWeather = allDaysWeather.get(dayWeatherDate);%>
    <%String demonstrationDate = WeatherProvider.getDemonstrationDateString(dayWeather.getCalendar());%>
    <%String dateString = WeatherProvider.getDateString(dayWeatherDate.getCalendar());%>
    <%Location location = dayWeatherDate.getLocation();%>
    <%String countryFirstUpper = Character.toUpperCase(location.getCountry().charAt(0)) + location.getCountry().substring(1);%>
    <tr>
        <td><%=countryFirstUpper%></td>
        <td><a href="DayForecastDispatcher?DateString=<%=dateString%>&Location=<%=location.getCountry()%>"><%=demonstrationDate%></a></td>
        <td><%=WeatherProvider.getWeekDay(dayWeather.getCalendar())%></td>
        <td><%=dayWeather.getTemperature()%></td>
        <td><%=dayWeather.getCloudiness()%></td>
        <td><%=dayWeather.getRainChance()%></td>
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
