package Controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DispatcherServlet", value = "/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {

    public void processLinkRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String link = request.getParameter("link");
        String path = "/WEB-INF/" + link;
        request.getRequestDispatcher(path).forward(request, response);

    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processLinkRequest(request, response);
    }
}
