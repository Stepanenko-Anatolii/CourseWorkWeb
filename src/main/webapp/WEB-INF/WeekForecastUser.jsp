<%@ page import="Models.DayWeatherModel" %>
<%@ page import="Logic.WeatherProvider" %>
<%@ page import="Models.WeatherPoint" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="Models.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Week forecast</title>
</head>
<body>

<%
    TreeMap<WeatherPoint, DayWeatherModel> weekWeatherLocation = (TreeMap<WeatherPoint, DayWeatherModel>) request.getAttribute("weatherWeek");
    Location location = weekWeatherLocation.firstEntry().getValue().getLocation();
    String countryFirstUpper = Character.toUpperCase(location.getCountry().charAt(0)) + location.getCountry().substring(1);
%>
<h4>Week forecast for <%=countryFirstUpper%></h4>
<table border="2">
    <tr>
        <th>Day of week</th>
        <th>Date</th>
        <th>Temperature</th>
        <th>cloudiness</th>
        <th>rain chance</th>
        <th>humidity</th>
    </tr>
<%--
    <c:forEach var="weatherPoint" items="${requestScope.weatherWeek.keySet()}}"></c:forEach>
--%>
    <%for(WeatherPoint dayWeatherDate: weekWeatherLocation.keySet()){%>
    <%DayWeatherModel dayWeather = weekWeatherLocation.get(dayWeatherDate);%>
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

<form action="AllForecastsDispatcher" method="post">
    <input type="hidden" name="location" value="<%=location.getCountry()%>">
    <input type="submit" value="All forecasts for <%=location.getCountry()%>">
</form><br>
<%--
<form method="post" action="DispatcherServlet">
    <input type="hidden" name="link" value="RegistrationView.jsp">
    <input type="submit" value="To register">
</form>
--%>
</body>
</html>
