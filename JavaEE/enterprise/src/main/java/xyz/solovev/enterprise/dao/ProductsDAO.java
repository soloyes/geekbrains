package xyz.solovev.enterprise.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.entity.MyEntity;
import xyz.solovev.enterprise.entity.Products;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class ProductsDAO extends AbstractDAO {

    @Override
    @Nullable
    public List<Products> getAll() {
        List<Products> list = em.createQuery("SELECT e FROM Products e", Products.class).getResultList();
        if (list.size() == 0) {
            Products product = new Products();
            product.setDescription("Product to persists new products");
            add(product);
        }
        return list;
    }

    @Override
    public MyEntity add(@NotNull MyEntity entity) {
        Products product = new Products((Products) entity);
        return em.merge(product);
    }

    @Override
    @Nullable
    public Products getById(@NotNull final Long id) {
        if (id < 0) return null;
        return getEntity(
                em.createQuery(
                        "SELECT e FROM Products e WHERE e.id=:id",
                        Products.class
                ).setParameter("id", id));
    }

    @Override
    public void removeById(@NotNull final Long id) {
        if (id < 0) return;
        final Products product = getById(id);
        em.remove(product);
    }

    @Override
    public void modify(MyEntity entity) {
        em.merge(entity);
    }
}