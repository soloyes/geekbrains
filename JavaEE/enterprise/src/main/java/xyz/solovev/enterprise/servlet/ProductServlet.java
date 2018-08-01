package xyz.solovev.enterprise.servlet;

import xyz.solovev.enterprise.utils.LogSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = ProductServlet.NAME, urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    public static final String NAME = "productServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogSystem.getLogger().info("Enter productServlet");
        req.getRequestDispatcher("view/static/product.jsp").forward(req, resp);
    }
}
