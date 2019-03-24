package com.geekbrains.geekmarket.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;
import sun.rmi.transport.TransportConstants;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

//@Configuration
@EnableJms
public class JmsConfig {
    @Bean
    public JmsListenerContainerFactory<?> jmsFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

//    @Bean
//    private ActiveMQQueue geekMarketMQ() {
//        return new ActiveMQQueue("gekmraket");
//    }
//
//    @Bean
//    private ConnectionFactory connectionFactory() {
//        Map<String, Object> connDetails = new HashMap<>();
//        connDetails.put(TransportConstants.HOST_PROP_NAME, "127.0.0.1");
//        connDetails.put(TransportConstants.PORT_PROP_NAME, "5445");
//        TransportConfiguration transportConfiguration = new TransportConfiguration(
//                NettyConnectorFactory.class.getName(), connDetails);
//        return new HornetQJMSConnectionFactory(false, transportConfiguration);
//    }
//
//    @Bean
//    private JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setConcurrency("3-5");
//        return factory;
//    }
//
//    @Bean
//    JmsTemplate jmsTemplate() {
//        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
//        jmsTemplate.setDefaultDestination(geekMarketMQ());
//        return jmsTemplate;
//    }
}
