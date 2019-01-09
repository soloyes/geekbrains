package com.geekbrains.geekmarket.config;

import com.geekbrains.geekmarket.jms.JmsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan("org.geekbrains.geekmarket")
@Import({JmsConfig.class, MailConfig.class})
public class AppConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/images/**")) {
            registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
        }
    }
}
