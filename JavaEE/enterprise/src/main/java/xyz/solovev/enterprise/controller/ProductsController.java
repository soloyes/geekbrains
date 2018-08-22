package xyz.solovev.enterprise.controller;

import lombok.Data;
import xyz.solovev.enterprise.dao.ProductsDAO;
import xyz.solovev.enterprise.entity.Products;

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

    private String category;

    public void reload() {
        productsList = productsDAO.getAllByCategory(category);
    }

    public void del(String id) {
        productsDAO.removeById(id);
        reload();
    }
}
