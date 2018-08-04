package xyz.solovev.enterprise.servlet;

import xyz.solovev.enterprise.utils.Attributes;
import xyz.solovev.enterprise.utils.LogSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = OrderServlet.NAME, urlPatterns = "/orders")
public class OrderServlet extends HttpServlet {
    public static final String NAME = Attributes.ORDERS_SERVLET;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogSystem.getLogger().info("Enter " + Attributes.ORDERS_SERVLET);
        req.setAttribute(Attributes.PAGE_ATTRIBUTE, Attributes.ORDERS);
        req.getRequestDispatcher("WEB-INF/view/static/orders.jsp").forward(req, resp);
    }
}
