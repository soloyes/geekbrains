package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.*;
import com.geekbrains.geekmarket.repositories.OrderStatusRepository;
import com.geekbrains.geekmarket.services.*;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private ShoppingCartService shoppingCartService;
    private OrderStatusService orderStatusService;
    private UserServiceImpl userService;
    private OrderItemService orderItemService;

    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Autowired
    public void setOrderRepository(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setOrderStatusRepository(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/make")
    public String cartPage(HttpSession session) {
        ShoppingCart shoppingCart = shoppingCartService.getCurrentCart(session);
        if (shoppingCart.getProducts().size() == 0) {
            return "redirect:/shop/cart";
        } else {
            Order order = new Order();
            OrderStatus orderStatus = orderStatusService.getOrderStatus(1L).get();
            order.setCreateAt(new Date());
            order.setOrderStatus(orderStatus);
            User user = userService.findByUserName("admin");
            order.setUser(user);

            Map<Product, Integer> products = shoppingCart.getProducts();
            Order savedOrder = orderService.saveOrder(order);

            for (Map.Entry<Product, Integer> map : products.entrySet()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setProduct(map.getKey());
                orderItem.setTotalPrice(map.getKey().getPrice() * map.getValue());
                orderItem.setQuantity(Long.valueOf(map.getValue()));
                orderItemService.saveOrderItem(orderItem);
            }

            shoppingCart.removeAll();
            return "redirect:/shop";
        }

    }
}
