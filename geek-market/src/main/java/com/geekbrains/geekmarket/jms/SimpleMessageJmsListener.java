package com.geekbrains.geekmarket.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

//@Component
public class SimpleMessageJmsListener {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageJmsListener.class);

    @JmsListener(destination = "geekmarket", containerFactory = "jmsFactory")
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            logger.info(">>> Received from Queue: " + textMessage.getText());
        } catch (JMSException ex) {
            logger.error("JMS error", ex);
        }
    }

    @JmsListener(destination = "geekmarket.topic", containerFactory = "jmsFactory")
    public void onMessageFromTopic(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            logger.info(">>> Received from Topic: " + textMessage.getText());
        } catch (JMSException ex) {
            logger.error("JMS error", ex);
        }
    }
}
