package com.geekbrains.geekmarket.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.geekbrains.geekmarket.entities.Order;
import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.entities.User;
import com.geekbrains.geekmarket.services.OrderService;
import com.geekbrains.geekmarket.services.ProductService;
import com.geekbrains.geekmarket.services.ShoppingCartService;
import com.geekbrains.geekmarket.services.UserService;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private OrderService orderService;
    private UserService userService;

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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String shopPage(Model model) {
        return "shop-page";
    }

    @GetMapping("/cart")
    public String cartPage(Model model, HttpServletRequest httpServletRequest) {
        return "cart";
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

//    @GetMapping("/order/fill")
//    public String orderFill(Model model, HttpServletRequest httpServletRequest, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        User user = userService.findByUserName(principal.getName());
//        ShoppingCart cart = shoppingCartService.getCurrentCart(httpServletRequest.getSession());
//        model.addAttribute("cart", cart);
//        return "order-filler";
//    }

    @PostMapping("/ready")
    public String showResult(@RequestParam(name = "response") String resp, HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute("details", resp);
        System.out.println(resp);
        return "order-result";
    }

    @GetMapping("/ready")
    public String getShowResult(Model model, HttpServletRequest httpServletRequest) {
        String details = httpServletRequest.getSession().getAttribute("details").toString();
        System.out.println("D: " + details);
        model.addAttribute("details", details);
        return "order-result";
    }

    @GetMapping("/order/fill")
    public String orderFill(Model model, HttpServletRequest httpServletRequest, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal.getName());
        Order order = orderService.makeOrder(shoppingCartService.getCurrentCart(httpServletRequest.getSession()), user);
        model.addAttribute("order", order);
        return "order-filler";
    }

    @PostMapping("/order/confirm")
    public String orderConfirm(Model model, HttpServletRequest httpServletRequest, @ModelAttribute(name = "order") Order orderFromFrontend, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal.getName());
        Order order = orderService.makeOrder(shoppingCartService.getCurrentCart(httpServletRequest.getSession()), user);
        order.setDeliveryAddress(orderFromFrontend.getDeliveryAddress());
        order.setPhoneNumber(orderFromFrontend.getPhoneNumber());
        order = orderService.saveOrder(order);
        model.addAttribute("order", order);
        return "order-filler";
    }
}
