package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Category;
import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.services.CategoryService;
import com.geekbrains.geekmarket.services.ProductService;
import com.geekbrains.geekmarket.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String shopPage(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "shop-page";
    }

    @GetMapping("/cart")
    public String cartPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("cart", shoppingCartService.getCurrentCart(httpServletRequest.getSession()));
        return "cart-page";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCartById(Model model, HttpServletRequest httpServletRequest, @PathVariable(name = "id") Long id) {
        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
        return "redirect:/shop";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCartById(Model model, HttpServletRequest httpServletRequest, @PathVariable(name = "id") Long id) {
        shoppingCartService.removeFromCart(httpServletRequest.getSession(), id);
        return "redirect:/shop/cart";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addProduct(Model model) {
        List<Category> allCategories = categoryService.getAllCategories();
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", allCategories);
        return "add-page";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addProduct(Product product) {
        product.setCreateAt(new Date());
        productService.addProduct(product);
        return "redirect:/shop";
    }

    @RequestMapping(path = "/del/{id}", method = RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long productId) {
        productService.removeProductById(productId);
        return "redirect:/shop/del";
    }

    @RequestMapping(path = "/del", method = RequestMethod.GET)
    public String delProduct(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "del-page";
    }
}
