package xyz.solovev.enterprise.dao;

import xyz.solovev.enterprise.entity.MyEntity;

import java.util.List;

public interface CRUD {
    List<? extends MyEntity> getAll();

    MyEntity persists(final MyEntity entity);

    MyEntity getById(final String id);

    void removeById(final String id);

    void merge(MyEntity entity);
}
