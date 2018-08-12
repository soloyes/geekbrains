package xyz.solovev.enterprise.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Categories implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
