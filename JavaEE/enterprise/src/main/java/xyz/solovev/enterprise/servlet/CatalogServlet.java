package xyz.solovev.enterprise.servlet;

import xyz.solovev.enterprise.utils.MenuAttributes;
import xyz.solovev.enterprise.utils.LogSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = CatalogServlet.NAME, urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {
    public static final String NAME = "CatalogServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogSystem.getLogger().info("Enter " + NAME);
        req.setAttribute(MenuAttributes.PAGE_ATTRIBUTE, MenuAttributes.CATALOG);
        req.getRequestDispatcher("WEB-INF/view/static/catalog.jsp").forward(req, resp);
    }
}
