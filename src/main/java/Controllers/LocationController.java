package Controllers;

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

@WebServlet(name = "LocationController", value = "/LocationController")
public class LocationController extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        Location location = new Location(country);

        WeatherDataObject weatherDB = new WeatherData();
        WeatherService weatherData = weatherDB.getAllWeatherByLocation(location);

        TreeMap<WeatherPoint, DayWeatherModel> weekWeatherLocation = weatherData.getDaysWeatherWeekLocation(location);


        //request.setAttribute("location", location.getCountry());
        request.setAttribute("weatherWeek", weekWeatherLocation);
        request.getRequestDispatcher("/WEB-INF/WeekForecastUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
