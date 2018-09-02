public class Main {

    public static void main(String[] args) {
        EntityPersistence entityPersistence = new EntityPersistence();
        Entity entity = new Entity();
        entity.setId(10);
        entityPersistence.insert(entity);
        System.out.println(entityPersistence.getById(10).get().getId());
        System.out.println(entityPersistence.getById(11).get().getId());

        Entity entity1 = new Entity();
        entity1.setId(10);
        entityPersistence.update(entity1);

        entityPersistence.delete(entity1);
        System.out.println(entityPersistence.getById(10).get().getId());
    }
}
