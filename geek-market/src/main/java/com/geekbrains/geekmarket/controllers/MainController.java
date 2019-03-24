package com.geekbrains.geekmarket.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

// К финалу:
// Безопасность hidden полей (надо ли делать DTO для order) +hidden поля убраны, DTO не нужно
// Картинки для товаров +
// Фильтры для поиска
// Подключенные платежные системы (PayPal) +
// Скорректировать страницу подтверждения покупки +
// Профиль пользователя (в каком состоянии заказы)
// Жизненный цикл заказа(уведомление покупателя)
// Поправить пути к картинкам +
// Сделать сохранение истории в таблицу
// Выпилить в контроллере формы для товаров кусок загрузки файлов +
// Поправить Order cascade detached entity ... +
// Подсчет итоговой стоимости заказа +
// rest security (вариант Юрия), из варианта Юрия взять отображение корзины в навигации и количества позиций
// добавить активацию пользователей через email

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class.getName());

    @RequestMapping("/")
    @CrossOrigin
    public String showHomePage() {
        return "index";
    }
}
