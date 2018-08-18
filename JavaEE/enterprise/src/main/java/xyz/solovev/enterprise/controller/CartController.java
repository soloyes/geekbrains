package xyz.solovev.enterprise.controller;

import lombok.Data;
import xyz.solovev.enterprise.entity.Products;

import javax.faces.bean.SessionScoped;
import java.util.LinkedHashMap;
import java.util.Map;

@SessionScoped
@Data
public class CartController {

    private Map<Products, Integer> productsMap;

    public CartController() {
        this.productsMap = new LinkedHashMap<>();
    }

    public void put(Products product) {
        if (!productsMap.containsKey(product)) {
            productsMap.put(product, 1);
        } else {
            productsMap.replace(product, productsMap.get(product) + 1);
        }
    }

    public Double getTotal() {
        double sum = 0.0;
        for (Map.Entry<Products, Integer> e : productsMap.entrySet()) {
            sum += e.getKey().getPrice() * e.getValue();
        }
        return sum;
    }
}
