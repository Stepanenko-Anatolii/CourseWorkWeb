<%@ page import="Models.DayWeatherModel" %>
<%@ page import="helpers.DateHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%DayWeatherModel dayWeather = (DayWeatherModel) request.getAttribute("dayWeather");%>
<%String demonstrationDate = DateHelper.getDemonstrationDateString(dayWeather.getCalendar());%>
<%String countryFirstUpper = Character.toUpperCase(dayWeather.getLocation().getCountry().charAt(0)) + dayWeather.getLocation().getCountry().substring(1);%>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Stylesheets/BasicStyle.css" type="text/css">
    <title><%=demonstrationDate%> forecast</title>
</head>
<body>

<h3>Weather forecast for <%=demonstrationDate%></h3>

<div><b>Location: </b><%=countryFirstUpper%></div>
<div><b>Day of week: </b><%=DateHelper.getWeekDay(dayWeather.getCalendar())%></div>
<div><b>Date: </b><%=demonstrationDate%></div>
<div><b>Temperature: </b><%=dayWeather.getTemperature()%> ℃</div>
<div><b>Cloudiness: </b><%=dayWeather.getCloudiness()%></div>
<div><b>Rain chance: </b><%=dayWeather.getRainChance()%>%</div>
<div><b>Humidity: </b><%=dayWeather.getHumidity()%>%</div>
<div><b>Wind speed: </b><%=dayWeather.getWindSpeed()%> km/h</div>
<div><b>Atmosphere pressure: </b><%=dayWeather.getAtmospherePressure()%> mmHg</div>
<div><b>Rainfall: </b><%=dayWeather.getRainfall()%> mm</div>

</body>
</html>
