package xyz.solovev.enterprise.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Categories implements MyEntity {

    public Categories() {
    }

    public Categories(Categories category) {
        this.name = category.name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
