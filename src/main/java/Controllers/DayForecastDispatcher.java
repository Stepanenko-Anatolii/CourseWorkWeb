package Controllers;

import Models.DayWeatherModel;
import Models.WeatherModel;
import dal.WeatherData;
import dal.WeatherDataObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DayForecastDispatcher", value = "/DayForecastDispatcher")
public class DayForecastDispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateString = request.getParameter("DateString");

        WeatherDataObject weatherDB = new WeatherData();
        WeatherModel weatherData = weatherDB.getAllWeather();

        DayWeatherModel dayWeather = weatherData.getDayWeatherModel(dateString);

        request.setAttribute("dayWeather", dayWeather);
        String path = "/WEB-INF/" + "DayForecast.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
