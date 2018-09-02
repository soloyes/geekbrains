import java.util.Objects;
import java.util.Random;

public class Entity {
    private static Random random = new Random();

    private int id = random.nextInt();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Entity)) return false;
        Entity entity = (Entity) object;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
