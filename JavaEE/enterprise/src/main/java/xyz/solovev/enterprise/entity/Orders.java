package xyz.solovev.enterprise.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Orders implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String customer_id;

    @Column
    private String product_id;
}