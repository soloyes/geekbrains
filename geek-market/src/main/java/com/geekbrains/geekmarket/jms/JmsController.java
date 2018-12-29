package com.geekbrains.geekmarket.jms;

import com.geekbrains.geekmarket.entities.Order;
import com.geekbrains.geekmarket.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// http://localhost:8161/admin

@Controller
@RequestMapping("/jms")
public class JmsController {
    private static final Logger logger = LoggerFactory.getLogger(JmsController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    private OrderRepository orderRepository;

    public void setOrderRepository(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/send")
    public String send() {
        List<Long> ordersIDs = new ArrayList<>();
        for (Order o : orderRepository.findAll()) {
            if (o.getStatus().getId() == 1L) {
                ordersIDs.add(o.getId());
            }
        }
        return "redirect:/";
    }
}
