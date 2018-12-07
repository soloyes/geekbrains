package com.geekbrains.geekmarket.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

// Корректировки:
// Поправить пути к картинкам
// Исправить корзину
// Сделать сохранение истории в таблицу
// Выпилить в контроллере формы для товаров кусок загрузки файлов
// Добавление валидации к товарам
// rest security

// Домашнее задание:
// Добавить логику формирования заказов

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class.getName());

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/favicon.ico";
    }
}
