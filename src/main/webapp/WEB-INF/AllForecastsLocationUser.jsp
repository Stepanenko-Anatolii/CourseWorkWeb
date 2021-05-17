<%@ page import="Models.DayWeatherModel" %>
<%@ page import="helpers.DateHelper" %>
<%@ page import="Models.Location" %>
<%@ page import="Models.WeatherPoint" %>
<%@ page import="java.util.TreeMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Stylesheets/BasicStyle.css" type="text/css">
    <title>All forecasts</title>
</head>
<body>

<%
    TreeMap<WeatherPoint, DayWeatherModel> allDaysWeather = (TreeMap<WeatherPoint, DayWeatherModel>) request.getAttribute("allDaysWeather");
    Location location = allDaysWeather.firstEntry().getValue().getLocation();
    String countryFirstUpper = Character.toUpperCase(location.getCountry().charAt(0)) + location.getCountry().substring(1);
%>
<h4>All forecasts for <%=countryFirstUpper%></h4>
<table>
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
    <%String demonstrationDate = DateHelper.getDemonstrationDateString(dayWeather.getCalendar());%>
    <%String dateString = DateHelper.getDateString(dayWeatherDate.getCalendar());%>
    <tr>
        <td><%=DateHelper.getWeekDay(dayWeather.getCalendar())%></td>
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
