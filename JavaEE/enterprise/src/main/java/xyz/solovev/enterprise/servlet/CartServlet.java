package xyz.solovev.enterprise.servlet;

import xyz.solovev.enterprise.utils.LogSystem;
import xyz.solovev.enterprise.utils.MenuAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = CartServlet.NAME, urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    public static final String NAME = "CartServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogSystem.getLogger().info("Enter "+ NAME);
        req.setAttribute(MenuAttributes.PAGE_ATTRIBUTE, MenuAttributes.CART);
        req.getRequestDispatcher("WEB-INF/view/jsp/cart.jsp").forward(req, resp);
    }
}
