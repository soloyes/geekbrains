package xyz.solovev.enterprise.controller;

import lombok.Data;
import xyz.solovev.enterprise.dao.ProductsDAO;
import xyz.solovev.enterprise.entity.MyEntity;
import xyz.solovev.enterprise.entity.Products;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ViewScoped
@ManagedBean
@Data
public class ProductsController {
    @Inject
    private ProductsDAO productsDAO;

    private List<Products> productsList;

    private Products selectedProduct;

    @PostConstruct
    private void init() {
        productsList = productsDAO.getAll();
    }

    public void add(Products product) {
        productsDAO.add(product);
        init();
    }

    public void del(Long id) {
        productsDAO.removeById(id);
        init();
    }

    public void modify(Products product) {
        productsDAO.modify(product);
        init();
    }
}
