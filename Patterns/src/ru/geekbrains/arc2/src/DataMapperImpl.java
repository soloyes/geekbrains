import java.util.Optional;

public final class DataMapperImpl implements DataMapper<Entity> {

    @Override
    public Optional<Entity> getById(int id) {
        System.out.println("SELECT");
        return Optional.of(new Entity());
    }

    @Override
    public void insert(Entity o) {
        System.out.println("INSERT");
    }

    @Override
    public void update(Entity o) {
        System.out.println("UPDATE");
    }

    @Override
    public void delete(Entity o) {
        System.out.println("DELETE");
    }
}
