package dispatchers;

import Models.DayWeatherModel;
import Models.Location;
import Logic.WeatherService;
import Models.WeatherPoint;
import dal.WeatherData;
import dal.WeatherDataObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.TreeMap;

@WebServlet(name = "AllForecastsDispatcher", value = "/AllForecastsDispatcher")
public class AllForecastsDispatcher extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("location");
        Location location = new Location(country);
        WeatherDataObject weatherDB = new WeatherData();
        WeatherService weatherData = weatherDB.getAllWeatherByLocation(location);
        TreeMap<WeatherPoint, DayWeatherModel> allDaysWeather = weatherData.getDaysWeather();

        request.setAttribute("allDaysWeather", allDaysWeather);

        String path = "/WEB-INF/AllForecastsLocationUser.jsp";
        request.getRequestDispatcher(path).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
