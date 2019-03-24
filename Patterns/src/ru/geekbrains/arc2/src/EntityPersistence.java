import java.util.Optional;

public class EntityPersistence implements IdentityMap<Entity>{
    private DataMapper<Entity> dataMapper = new DataMapperImpl();

    private IdentityMap<Entity> entityIdentityMap = new IdentityMapImpl();

    @Override
    public Optional<Entity> getById(int id) {
        if (entityIdentityMap.getById(id).isPresent()) {
            return entityIdentityMap.getById(id);
        }
        else {
            return dataMapper.getById(id);
        }
    }

    @Override
    public void insert(Entity entity) {
        entityIdentityMap.insert(entity);
        dataMapper.insert(entity);
    }

    @Override
    public void update(Entity entity) {
        entityIdentityMap.update(entity);
        dataMapper.update(entity);
    }

    @Override
    public void delete(Entity entity) {
        entityIdentityMap.delete(entity);
        dataMapper.delete(entity);
    }
}
