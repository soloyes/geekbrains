package ru.geekbrains.arc1.utils;

public interface IdentityMap {
    boolean exists(Object object);

    void add(Object object);

    void remove(Object object);
}
