package xyz.solovev.enterprise.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractDAO implements CRUD {

    @NotNull
    private static final String PERSISTENCE_UNIT = "mysql_persistence";

    @PersistenceContext(unitName = PERSISTENCE_UNIT)
    protected EntityManager em;

    @Nullable
    public <T> T getEntity(@NotNull final TypedQuery<T> query) {
        final List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) return null;
        return resultList.get(0);
    }
}
