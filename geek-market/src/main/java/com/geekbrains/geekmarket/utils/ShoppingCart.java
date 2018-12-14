package com.geekbrains.geekmarket.utils;

import com.geekbrains.geekmarket.entities.OrderItem;
import com.geekbrains.geekmarket.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<OrderItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void add(Product product) {
        OrderItem orderItem = items.stream().filter(o -> o.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(0L);
            orderItem.setId((long) items.size());
            orderItem.setTotalPrice(0.0);
            items.add(orderItem);
        }
        orderItem.setQuantity(orderItem.getQuantity() + 1);
    }

    public void remove(Product product) {
        OrderItem orderItem = items.stream().filter(o -> o.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
        if (orderItem != null) {
            items.remove(orderItem);
        }
    }

    public void decrease(Product product) {
        OrderItem orderItem = items.stream()
                .filter(o -> o.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (orderItem != null) {
            orderItem.setQuantity(orderItem.getQuantity() - 1);
            if (orderItem.getQuantity() == 0){
                remove(product);
            }
        }
    }

    public void increase(Product product) {
        add(product);
    }
}
