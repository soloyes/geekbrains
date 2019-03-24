package com.geekbrains.geekmarket.jms;

import com.geekbrains.geekmarket.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jms.TextMessage;

// http://localhost:8161/admin

//@Controller
@RequestMapping("/jms")
public class JmsController {
    private static final Logger logger = LoggerFactory.getLogger(JmsController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/send")
    public String send() {
        jmsTemplate.send("geekmarket", session -> {
            TextMessage jmsMessage = session.createTextMessage("Hello");
            logger.info(">>> Sending: " + jmsMessage.getText());
            return jmsMessage;
        });

        jmsTemplate.send("geekmarket.topic", session -> {
            TextMessage jmsMessage = session.createTextMessage("Hello Topic");
            logger.info(">>> Sending to topic: " + jmsMessage.getText());
            return jmsMessage;
        });

        jmsTemplate.convertAndSend("geekmarket", new Product());
        return "redirect:/";
    }
}
