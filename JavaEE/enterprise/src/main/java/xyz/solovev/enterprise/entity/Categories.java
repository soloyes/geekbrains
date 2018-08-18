package xyz.solovev.enterprise.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Categories extends AbstractEntity {

    public Categories() {
    }

    public Categories(Categories category) {
        this.name = category.name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products> products = new ArrayList<>();

    @Column
    private String name;
}