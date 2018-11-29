package com.geekbrains.geekmarket.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Around("execution(public void com.geekbrains.geekmarket.services.ProductService.addProduct(..))")
    public void addProduct(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Product add start.");
        joinPoint.proceed();
        System.out.println("Product add end.");
        System.out.println("That was " + joinPoint.getArgs()[0]);
    }

    @Around("execution(public void com.geekbrains.geekmarket.services.ProductService.removeProductById(..))")
    public void removeProductById(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Product del start.");
        joinPoint.proceed();
        System.out.println("Product del end.");
        System.out.println("That was product with id=" + joinPoint.getArgs()[0]);
    }
}