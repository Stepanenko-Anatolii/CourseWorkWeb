package Controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuthorizationController", value = "/AuthorizationController")
public class AuthorizationController extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login.equals("admin") && password.equals("strongPass")){
            request.getRequestDispatcher("/WEB-INF/AllForecastsAdmin.jsp").forward(request, response);
        }else{
            PrintWriter out = response.getWriter();
            String someMessage = "Wrong login or password. Please try again";
            out.println("<script type='text/javascript'>");
            out.println("alert(" + "'" + someMessage + "'" + ");</script>");
            //response.sendRedirect("Authorization.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
