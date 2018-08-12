package xyz.solovev.enterprise.servlet;

import xyz.solovev.enterprise.dao.ProductsDAO;
import xyz.solovev.enterprise.entity.Products;
import xyz.solovev.enterprise.utils.MenuAttributes;
import xyz.solovev.enterprise.utils.LogSystem;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = ProductsServlet.NAME, urlPatterns = "/products")
public class ProductsServlet extends HttpServlet {
    public static final String NAME = "ProductsServlet";

    @Inject
    private ProductsDAO productsDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogSystem.getLogger().info("Enter " + NAME);
        Collection<Products> productsDTOS = productsDAO.getAll();
        req.setAttribute(MenuAttributes.PAGE_ATTRIBUTE, MenuAttributes.PRODUCTS);
        req.setAttribute(MenuAttributes.PRODUCTS, productsDTOS);
        req.getRequestDispatcher("WEB-INF/view/jsp/products.jsp").forward(req, resp);
    }
}
