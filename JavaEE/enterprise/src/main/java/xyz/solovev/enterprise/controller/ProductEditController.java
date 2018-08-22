package xyz.solovev.enterprise.controller;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.dao.ProductsDAO;
import xyz.solovev.enterprise.entity.Categories;
import xyz.solovev.enterprise.entity.Products;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ViewScoped
@ManagedBean
@Data
public class ProductEditController {
    @Inject
    private ProductsDAO productsDAO;

    @NotNull
    private String category = "";

    @Nullable
    private String prodID;

    @Nullable
    private Products product;

    public void init() {
        product = productsDAO.getById(prodID);
        if (product == null) product = new Products();
    }

    @NotNull
    public String save() {
        Categories category = new Categories();
        category.setId(this.category);
        if (product != null) product.setCategory(category);
        if (prodID == null) {
            productsDAO.persists(product);
        } else {
            productsDAO.merge(product);
        }
        return "/WEB-INF/view/jsf/categories_list";
    }
}
