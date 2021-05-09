package Controllers;

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
import java.util.TreeMap;

@WebServlet(name = "AllForecastsAdminDispatcher", value = "/AllForecastsAdminDispatcher")
public class AllForecastsAdminDispatcher extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WeatherDataObject weatherDB = new WeatherData();
        WeatherModel weatherData = weatherDB.getAllWeather();
        TreeMap<WeatherPoint, DayWeatherModel> allDaysWeather = weatherData.getDaysWeather();

        request.setAttribute("allDaysWeather", allDaysWeather);

        String path = "/WEB-INF/AllForecastsAdmin.jsp";
        request.getRequestDispatcher(path).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
