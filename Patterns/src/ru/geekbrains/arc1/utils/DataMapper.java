package ru.geekbrains.arc1.utils;

import ru.geekbrains.arc1.exception.NoSchemaException;

public interface DataMapper {
    void insert(Object object) throws NoSchemaException;

    void update(Object object) throws NoSchemaException;

    void delete(Object object) throws NoSchemaException;
}
