package xyz.solovev.enterprise.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.entity.Categories;
import xyz.solovev.enterprise.entity.MyEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CategoriesDAO extends AbstractDAO {
    @Override
    @Nullable
    public List<Categories> getAll() {
        List<Categories> list = em.createQuery("SELECT e FROM Categories e", Categories.class).getResultList();
        if (list.size() == 0) {
            Categories category = new Categories();
            category.setId("1");
            category.setName("Category to persists new categories");
            persists(category);
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
    public Categories getById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return getEntity(
                em.createQuery(
                        "SELECT e FROM Categories e WHERE e.id=:id",
                        Categories.class
                ).setParameter("id", id));
    }

    @Override
    public void removeById(@NotNull final String id) {
        final Categories category = getById(id);
        if (category == null) return;
        em.remove(category);
    }

    @Override
    public void merge(MyEntity entity) {
        em.merge(entity);
    }
}

