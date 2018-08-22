package xyz.solovev.enterprise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.entity.Categories;
import xyz.solovev.enterprise.entity.Products;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement
public class ProductsDTO {

    private String name;

    private String description;

    private Float price;

    private String categoryId;

    private String id;

    public ProductsDTO(Products product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        @Nullable Categories category = product.getCategory();
        if (category != null) this.categoryId = category.getId();
        this.id = product.getId();
    }
}
