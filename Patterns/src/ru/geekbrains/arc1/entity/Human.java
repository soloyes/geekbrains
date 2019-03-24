package ru.geekbrains.arc1.entity;

import ru.geekbrains.arc1.annotation.Column;
import ru.geekbrains.arc1.annotation.Data;
import ru.geekbrains.arc1.annotation.Id;

@Data
public class Human {
    @Id
    private String name;

    @Column
    private int age;

    @Column
    private Nationality nationality;
}
