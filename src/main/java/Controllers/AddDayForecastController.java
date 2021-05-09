package Controllers;

import Logic.WeatherProvider;
import Models.DayWeatherModel;
import Models.Location;
import dal.WeatherData;
import dal.WeatherDataObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "addDayForecast", value = "/add-day-forecast")
public class AddDayForecastController extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String country = request.getParameter("country");
        Location location = new Location(country);

        String date = request.getParameter("date");

        String temperature = request.getParameter("temperature");
        String humidity = request.getParameter("humidity");
        String windSpeed = request.getParameter("windSpeed");
        String atmospherePressure = request.getParameter("atmospherePressure");
        String rainChance = request.getParameter("rainChance");
        String rainfall = request.getParameter("rainfall");
        String cloudiness = request.getParameter("cloudiness");

        DayWeatherModel dayWeather = WeatherProvider.makeWeatherModel(location, date, temperature, humidity, windSpeed, atmospherePressure, rainChance, rainfall, cloudiness);
System.out.println("AddDayFore(32): loc: " + dayWeather.getLocation().getCountry() + "; date: "+WeatherProvider.getDemonstrationDateString(dayWeather.getCalendar()));
        //ДОБАВЛЕНИЕ В БАЗУ ДАННЫХ
        WeatherDataObject weatherDB = new WeatherData();
        weatherDB.putWeather(dayWeather);

        request.getRequestDispatcher("AllForecastsAdminDispatcher").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

}