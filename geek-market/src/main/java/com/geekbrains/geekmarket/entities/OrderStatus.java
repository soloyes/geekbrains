package com.geekbrains.geekmarket.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders_statuses")
@Data
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String user;
}
