package ru.geekbrains.arc1.entity;

import ru.geekbrains.arc1.annotation.Column;
import ru.geekbrains.arc1.annotation.Data;
import ru.geekbrains.arc1.annotation.Id;

@Data(name = "animals")
public class Animal {
    @Id
    private String nick;

    @Column
    private int age;

    @Column
    private AnimalType type;
}
