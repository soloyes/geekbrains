package xyz.solovev.enterprise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.solovev.enterprise.entity.Categories;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement
public class CategoriesDTO {

    private String name;

    private String id;

    public CategoriesDTO(Categories category){
        this.name = category.getName();
        this.id = category.getId();
    }
}
