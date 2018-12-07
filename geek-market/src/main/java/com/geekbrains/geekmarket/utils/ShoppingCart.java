package com.geekbrains.geekmarket.utils;

import com.geekbrains.geekmarket.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
    }

    // todo исправить кривизну удаления
    public void remove(Product product) {
        products.remove(product);
    }
}
