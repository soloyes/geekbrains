package ru.geekbrains.arc1;

import ru.geekbrains.arc1.entity.Animal;
import ru.geekbrains.arc1.entity.Human;
import ru.geekbrains.arc1.utils.PersistenceProvider;


/*
* 1) Create schema according entity classes
* 2) If no schema => unable to DML/DDL etc.. (DataMapperImpl.createSchema() must be called before SQL)
* 3) No concurrency, unfortunately. Will be in next release :)
* 4) IdentityMap can only add, delete. Unable to update. Will be in next release :)
*/

public class Main {
    public static void main(String[] args) {
        PersistenceProvider provider = new PersistenceProvider();
        provider.persists(new Animal());
        provider.persists(new Human());
        Animal animal = new Animal();
        Human human = new Human();
        provider.persists(animal);
        provider.persists(human);
        provider.delete(animal);
        provider.delete(human);
    }
}
