package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Order;
import com.geekbrains.geekmarket.services.OrderService;
import com.geekbrains.geekmarket.services.ProductService;
import com.geekbrains.geekmarket.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private OrderService orderService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public String shopPage(Model model) {
        return "shop-page";
    }

    @GetMapping("/cart")
    public String cartPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("cart", shoppingCartService.getCurrentCart(httpServletRequest.getSession()));
        return "cart-page-old";
    }

//    @GetMapping("/cart/add/{id}")
//    public String addProductToCartById(Model model, HttpServletRequest httpServletRequest, @PathVariable(name = "id") Long id) {
//        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
//        return "redirect:/shop";
//    }
//
//    @GetMapping("/cart/remove/{id}")
//    public String removeProductFromCartById(Model model, HttpServletRequest httpServletRequest, @PathVariable(name = "id") Long id) {
//        shoppingCartService.removeFromCart(httpServletRequest.getSession(), id);
//        return "redirect:/shop/cart";
//    }

    @GetMapping("/order")
    public String orderConfirm(Model model, HttpServletRequest httpServletRequest, Principal principal) {
        Order order = orderService.makeOrder(shoppingCartService.getCurrentCart(httpServletRequest.getSession()), principal);
        model.addAttribute("order", order);
        return "order-result";
    }
}
