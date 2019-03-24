import java.util.LinkedList;
import java.util.List;

import java.util.Optional;

public class IdentityMapImpl implements IdentityMap<Entity> {

    private static List<Entity> cache = new LinkedList<>();
    private static Entity tmp = new Entity();

    @Override
    public Optional<Entity> getById(int id) {
        tmp.setId(id);
        int i = cache.indexOf(tmp);
        if (i >= 0)
            return Optional.of(cache.get(cache.indexOf(tmp)));
        else return Optional.empty();
    }

    @Override
    public void insert(Entity entity) {
        tmp.setId(entity.getId());
        int i = cache.indexOf(tmp);
        if (i < 0)
            cache.add(entity);
    }

    @Override
    public void update(Entity entity) {
        if (getById(entity.getId()).isPresent()) {
            cache.remove(entity);
            cache.add(entity);
        }
    }

    @Override
    public void delete(Entity entity) {
        if (getById(entity.getId()).isPresent()) {
            cache.remove(entity);
        }
    }
}
