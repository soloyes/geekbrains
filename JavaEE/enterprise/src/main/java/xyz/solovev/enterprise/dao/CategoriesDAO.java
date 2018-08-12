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
            category.setName("Category to add new categories");
            add(category);
        }
        return list;
    }

    @Override
    @Nullable
    public MyEntity add(@Nullable MyEntity entity) {
        return em.merge(entity);
    }

    @Override
    @Nullable
    public Categories getById(@NotNull final Long id) {
        if (id < 0) return null;
        return getEntity(
                em.createQuery(
                        "SELECT e FROM Categories e WHERE e.id=:id",
                        Categories.class
                ).setParameter("id", id));
    }

    @Override
    public void removeById(@NotNull final Long id) {
        if (id < 0) return;
        final Categories category = getById(id);
        em.remove(category);
    }

    @Override
    public void modify(MyEntity entity) {

    }
}

