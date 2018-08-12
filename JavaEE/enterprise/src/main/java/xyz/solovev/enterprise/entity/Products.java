package xyz.solovev.enterprise.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Products implements MyEntity {

    public Products() {
    }

    public Products(Products product) {
        this.name = product.name;
        this.category_id = product.category_id;
        this.description = product.description;
        this.discount = product.discount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long category_id;

    @Column
    private String description;

    @Column
    private Float discount;
}
