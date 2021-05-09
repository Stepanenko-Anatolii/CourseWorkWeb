package Controllers;

import Logic.WeatherProvider;
import Models.DayWeatherModel;
import Models.Location;
import Models.WeatherModel;
import Models.WeatherPoint;
import dal.WeatherData;
import dal.WeatherDataObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.GregorianCalendar;

@WebServlet(name = "DayForecastDispatcher", value = "/DayForecastDispatcher")
public class DayForecastDispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateString = request.getParameter("DateString");
        GregorianCalendar calendar = WeatherProvider.getCalendarFromString(dateString, "\\.");
        String country = request.getParameter("Location");
        Location location = new Location(country);
        WeatherPoint weatherPoint = new WeatherPoint(calendar, location);

        WeatherDataObject weatherDB = new WeatherData();
        WeatherModel weatherData = weatherDB.getAllWeather();

        DayWeatherModel dayWeather = weatherData.getDayWeatherModel(weatherPoint);

        request.setAttribute("dayWeather", dayWeather);
        String path = "/WEB-INF/" + "DayForecast.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
