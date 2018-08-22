package xyz.solovev.enterprise.controller;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.dao.CategoriesDAO;
import xyz.solovev.enterprise.entity.Categories;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ViewScoped
@ManagedBean
@Data
public class CategoryEditController {
    @Inject
    private CategoriesDAO categoriesDAO;

    @Nullable
    private String id;

    @NotNull
    private Categories category = new Categories();

    public void init() {
        @Nullable final Categories category = categoriesDAO.getById(id);
        if (category != null) this.category = category;
    }

    @NotNull
    public String save() {
        if (category.getId().equals("1")) {
            Categories category = new Categories(this.category);
            categoriesDAO.persists(category);
        } else {
            categoriesDAO.merge(category);
        }
        return "/WEB-INF/view/jsf/categories_list";
    }
}
