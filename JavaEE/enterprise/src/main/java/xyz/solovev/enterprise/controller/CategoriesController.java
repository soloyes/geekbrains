package xyz.solovev.enterprise.controller;

import lombok.Data;
import xyz.solovev.enterprise.dao.CategoriesDAO;
import xyz.solovev.enterprise.entity.Categories;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ViewScoped
@ManagedBean
@Data
public class CategoriesController {
    @Inject
    private CategoriesDAO categoriesDAO;

    private List<Categories> categoriesList;

    private Categories selectedCategory;

    @PostConstruct
    private void reload() {
        categoriesList = categoriesDAO.getAll();
    }

    public void del(String id) {
        categoriesDAO.removeById(id);
        reload();
    }
}
