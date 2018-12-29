package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.Order;
import com.geekbrains.geekmarket.entities.OrderItem;
import com.geekbrains.geekmarket.entities.OrderStatus;
import com.geekbrains.geekmarket.entities.User;
import com.geekbrains.geekmarket.repositories.OrderRepository;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Random;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order makeOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setId(0L);
        order.setUser(user);
        OrderStatus os = new OrderStatus(); // todo исправить
        os.setId(1L);
        os.setTitle("Сформирован");
        order.setStatus(os);
        order.setPrice(cart.getTotalCost());
        order.setDeliveryAddress(cart.getDeliveryAddress());
        order = orderRepository.save(order);
        order.setOrderItems(new ArrayList<>(cart.getItems()));
        for (OrderItem o : cart.getItems()) {
            o.setOrder(order);
        }
        order = orderRepository.save(order);
        return order;
    }
}
