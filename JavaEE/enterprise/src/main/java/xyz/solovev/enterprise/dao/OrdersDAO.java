package xyz.solovev.enterprise.dao;

import org.jetbrains.annotations.NotNull;
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
    public MyEntity add(@Nullable MyEntity entity) {
        return em.merge(entity);
    }

    @Override
    @Nullable
    public Orders getById(@NotNull final Long id) {
        if (id < 0) return null;
        return getEntity(
                em.createQuery(
                        "SELECT e FROM Orders e WHERE e.id=:id",
                        Orders.class
                ).setParameter("id", id));
    }

    @Override
    public void removeById(@NotNull final Long id) {
        if (id < 0) return;
        final Orders order = getById(id);
        em.remove(order);
    }

    @Override
    public void merge(MyEntity entity) {
        em.merge(entity);
    }
}
