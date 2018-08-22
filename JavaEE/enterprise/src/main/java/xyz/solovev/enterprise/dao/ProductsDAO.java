package xyz.solovev.enterprise.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.entity.MyEntity;
import xyz.solovev.enterprise.entity.Products;
import xyz.solovev.enterprise.interceptor.LogInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.*;

@Stateless
@Interceptors({LogInterceptor.class})
public class ProductsDAO extends AbstractDAO {

    public List<Products> getAllByCategory(String categoryId) {
        return em
                .createQuery("SELECT e FROM Products e where e.category.id=:categoryId", Products.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    @Nullable
    public List<Products> getAll() {
        List<Products> list = em.createQuery("SELECT e FROM Products e", Products.class).getResultList();
        if (list.size() == 0) {
            Products product = new Products();
            product.setDescription("Product to persists new products");
            product.setId("1");
            persists(product);
        }
        return list;
    }

    @Override
    public MyEntity persists(@Nullable MyEntity entity) {
        if (entity == null) return null;
        em.persist(entity);
        return entity;
    }

    @Override
    @Nullable
    public Products getById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return getEntity(
                em.createQuery(
                        "SELECT e FROM Products e WHERE e.id=:id",
                        Products.class
                ).setParameter("id", id));
    }

    @Override
    public void removeById(@Nullable final String id) {
        @Nullable final Products product = getById(id);
        if (product == null) return;
        em.remove(product);
    }

    @Override
    public void merge(MyEntity entity) {
        em.merge(entity);
    }

    @Nullable
    public Products getByName(@Nullable final String name) {
        if (name == null || name.isEmpty()) return null;
        return getEntity(
                em.createQuery(
                        "SELECT e FROM Products e WHERE e.name=:name",
                        Products.class
                ).setParameter("name", name));
    }
}