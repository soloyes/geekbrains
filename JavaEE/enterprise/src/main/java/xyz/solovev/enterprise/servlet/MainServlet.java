package xyz.solovev.enterprise.servlet;

import xyz.solovev.enterprise.utils.MenuAttributes;
import xyz.solovev.enterprise.utils.LogSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = MainServlet.NAME, urlPatterns = {""})
public class MainServlet extends HttpServlet {
    public static final String NAME = "MainServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogSystem.getLogger().info("Enter " + NAME);
        req.setAttribute(MenuAttributes.PAGE_ATTRIBUTE, MenuAttributes.MAIN);
        req.getRequestDispatcher("WEB-INF/view/jsp/main.jsp").forward(req, resp);
    }
}
