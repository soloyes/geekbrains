package xyz.solovev.enterprise.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Products extends AbstractEntity {

    public Products() {
    }

    public Products(Products product) {
        this.name = product.name;
        this.description = product.description;
        this.price = product.price;
    }

    @ManyToOne
    private Categories category;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Products products = (Products) o;
        return Objects.equals(getId(), products.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
