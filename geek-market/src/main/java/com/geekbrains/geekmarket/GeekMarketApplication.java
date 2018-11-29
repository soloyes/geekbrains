package com.geekbrains.geekmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.geekbrains.geekmarket")
@EnableAspectJAutoProxy
public class GeekMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeekMarketApplication.class, args);
	}
}
