package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.services.CategoryService;
import com.geekbrains.geekmarket.services.ProductService;
import com.geekbrains.geekmarket.utils.ImageSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        if (id.equals(0L)) {
            Product product = new Product();
            product.setId(0L);
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", productService.getProductById(id));
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/edit-product";
    }

    @PostMapping("/edit")
    public String processProductAddForm(@Valid @ModelAttribute("product") Product product, BindingResult theBindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (product.getId() == 0 && productService.isProductWithTitleExists(product.getTitle())) {
            theBindingResult.addError(new ObjectError("product.title", "Товар с таким названием уже существует")); // todo не отображает сообщение
        }
        if (theBindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "edit-product";
        }
        if (!file.isEmpty()) {
            product.setImagePath(ImageSaver.saveFile(file));
        }
        productService.saveProduct(product);
        return "redirect:/shop";
    }
}
