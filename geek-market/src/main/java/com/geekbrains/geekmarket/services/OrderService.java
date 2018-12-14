package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.Order;
import com.geekbrains.geekmarket.entities.OrderItem;
import com.geekbrains.geekmarket.entities.OrderStatus;
import com.geekbrains.geekmarket.entities.User;
import com.geekbrains.geekmarket.repositories.OrderRepository;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserService userService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public Order makeOrder(ShoppingCart cart, Principal principal) {
        Order order = new Order();
        User user = userService.findByUserName(principal.getName());
        order.setUser(user);
        OrderStatus os = new OrderStatus(); // todo исправить
        os.setId(1L);
        os.setTitle("Сформирован");
        order.setStatus(os);
        order = orderRepository.save(order);

        order.setOrderItems(cart.getItems());
        for (OrderItem o : cart.getItems()) {
            o.setOrder(order);
        }
        order = orderRepository.save(order);
        return order;
    }
}
