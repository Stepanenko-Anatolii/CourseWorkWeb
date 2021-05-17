<%@ page import="Models.DayWeatherModel" %>
<%@ page import="helpers.DateHelper" %>
<%@ page import="Models.WeatherPoint" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="Models.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Stylesheets/BasicStyle.css" type="text/css">
    <title>All forecasts(admin page)</title>
</head>
<body>

<form method="post" action="DispatcherServlet">
    <input type="hidden" name="link" value="AddDayForecastView.jsp">
    <input type="submit" value="Add new forecast">
</form>

<h4>All forecasts:</h4>
<%TreeMap<WeatherPoint, DayWeatherModel> allDaysWeather = (TreeMap<WeatherPoint, DayWeatherModel>) request.getAttribute("allDaysWeather");%>
<table>
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
    <%String demonstrationDate = DateHelper.getDemonstrationDateString(dayWeather.getCalendar());%>
    <%String dateString = DateHelper.getDateString(dayWeatherDate.getCalendar());%>
    <%Location location = dayWeatherDate.getLocation();%>
    <%String countryFirstUpper = Character.toUpperCase(location.getCountry().charAt(0)) + location.getCountry().substring(1);%>
    <tr>
        <td><%=countryFirstUpper%></td>
        <td><a href="DayForecastDispatcher?DateString=<%=dateString%>&Location=<%=location.getCountry()%>"><%=demonstrationDate%></a></td>
        <td><%=DateHelper.getWeekDay(dayWeather.getCalendar())%></td>
        <td><%=dayWeather.getTemperature()%></td>
        <td><%=dayWeather.getCloudiness()%></td>
        <td><%=dayWeather.getRainChance()%></td>
    </tr>
    <%}%>
</table><br>

</body>
</html>
