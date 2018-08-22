package ru.geekbrains.iterator;


public interface Iterable {
    Object next();

    boolean hasNext();

    Object remove();
}
