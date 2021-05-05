package Controllers;

import Models.Location;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LocationController", value = "/LocationController")
public class LocationController extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        Location location = new Location(country);

        request.setAttribute("location", location.getCountry());
        request.getRequestDispatcher("/WEB-INF/WeekForecastUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
