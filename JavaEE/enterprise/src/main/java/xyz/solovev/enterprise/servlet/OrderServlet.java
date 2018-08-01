package xyz.solovev.enterprise.servlet;

import xyz.solovev.enterprise.utils.LogSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = OrderServlet.NAME, urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    public static final String NAME = "orderServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogSystem.getLogger().info("Enter orderServlet");
        req.getRequestDispatcher("view/static/order.jsp").forward(req, resp);
    }
}
