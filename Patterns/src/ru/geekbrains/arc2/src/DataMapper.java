import java.util.Optional;


public interface DataMapper<T> {

    Optional<T> getById(int id);

    void insert(T t);

    void update(T t);

    void delete(T t);
}