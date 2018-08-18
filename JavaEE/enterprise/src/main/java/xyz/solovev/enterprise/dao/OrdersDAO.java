package xyz.solovev.enterprise.dao;

import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.entity.MyEntity;
import xyz.solovev.enterprise.entity.Orders;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrdersDAO extends AbstractDAO {

    @Override
    @Nullable
    public List<Orders> getAll() {
        return em.createQuery("SELECT e FROM Orders e", Orders.class).getResultList();
    }

    @Override
    public MyEntity persists(@Nullable MyEntity entity) {
        if (entity == null) return null;
        em.persist(entity);
        return entity;
    }

    @Override
    @Nullable
    public Orders getById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return getEntity(
                em.createQuery(
                        "SELECT e FROM Orders e WHERE e.id=:id",
                        Orders.class
                ).setParameter("id", id));
    }

    @Override
    public void removeById(@Nullable final String id) {
        @Nullable final Orders order = getById(id);
        if (order == null) return;
        em.remove(order);
    }

    @Override
    public void merge(MyEntity entity) {
        em.merge(entity);
    }
}
