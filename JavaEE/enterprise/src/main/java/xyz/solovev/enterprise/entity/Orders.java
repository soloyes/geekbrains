package xyz.solovev.enterprise.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Orders extends AbstractEntity {

    @Column
    private String customer;

    @Column
    private String product;
}