package ru.geekbrains.arc1.utils;

public class PersistenceProvider {
    private DataMapper mapper;
    private IdentityMap identityMap;

    public PersistenceProvider() {
        this.mapper = new DataMapperImpl();
        this.identityMap = new IdentityMapImpl();
        DataMapperImpl.createSchema();
    }

    public void persists(Object object) {
        if (!identityMap.exists(object)) {
            mapper.insert(object);
            identityMap.add(object);
        }
        System.out.println(identityMap);
    }

    public void delete(Object object) {
        if (identityMap.exists(object)) {
            mapper.delete(object);
            identityMap.remove(object);
        }
        System.out.println(identityMap);
    }

    public void update(Object object) {
        mapper.update(object);
    }
}
