package Controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AuthorizationController", value = "/AuthorizationController")
public class AuthorizationController extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login.equals("admin") && password.equals("pass")){
            request.getRequestDispatcher("AllForecastsAdminDispatcher").forward(request, response);
        }else{
            request.setAttribute("falseData", true);
            request.getRequestDispatcher("Authorization.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
