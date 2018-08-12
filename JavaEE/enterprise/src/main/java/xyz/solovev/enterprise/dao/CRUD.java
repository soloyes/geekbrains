package xyz.solovev.enterprise.dao;

import xyz.solovev.enterprise.entity.MyEntity;

import java.util.List;

public interface CRUD {
    List<? extends MyEntity> getAll();

    MyEntity add(final MyEntity entity);

    MyEntity getById(final Long id);

    void removeById(final Long id);

    void merge(MyEntity entity);
}
