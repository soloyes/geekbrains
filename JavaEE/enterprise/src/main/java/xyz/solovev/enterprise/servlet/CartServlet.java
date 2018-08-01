package xyz.solovev.enterprise.servlet;

import xyz.solovev.enterprise.utils.LogSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = CartServlet.NAME, urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    public static final String NAME = "cartServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogSystem.getLogger().info("Enter cartServlet");
        req.getRequestDispatcher("view/static/cart.jsp").forward(req, resp);
    }
}
