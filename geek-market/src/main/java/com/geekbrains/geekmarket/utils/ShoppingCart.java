package com.geekbrains.geekmarket.utils;

import com.geekbrains.geekmarket.entities.Product;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ShoppingCart {
    private Map<Product, Integer> products;

    public ShoppingCart() {
        products = new LinkedHashMap<>();
    }

    public void add(Product product) {
        products.merge(product, 1, (a, b) -> a + b);
    }

    // todo исправить кривизну удаления
    public void remove(Product product) {
        products.remove(product);
    }

    public void decrease(Product product) {
        if (products.get(product) == 1) {
            remove(product);
        } else {
            products.merge(product, -1, (a, b) -> a + b);
        }
    }

    public void increase(Product product) {
        add(product);
    }

    public void removeAll(){
        products.clear();
    }
}
