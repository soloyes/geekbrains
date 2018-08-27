package ru.geekbrains.arc1.utils;

import ru.geekbrains.arc1.annotation.Column;
import ru.geekbrains.arc1.annotation.Data;
import ru.geekbrains.arc1.annotation.Id;
import ru.geekbrains.arc1.exception.NoSchemaException;

import java.lang.reflect.Field;
import java.util.*;

public class DataMapperImpl implements DataMapper {

    private static List<Map<String, String>> schema;

    private static List<Map<String, Class<?>>> classList;

    public static void createSchema() {
        if (classList == null) {
            classList = new ArrayList<>();
            ClassLoader loader = new SimpleLoader();
            for (Class clazz : loader.load("ru.geekbrains.arc1")) {
                if (clazz.isAnnotationPresent(Data.class)) {
                    Data data = (Data) clazz.getAnnotation(Data.class);
                    Map<String, Class<?>> map = new LinkedHashMap<>();
                    if (data.name().isEmpty()) {
                        map.put(clazz.getSimpleName().toLowerCase(), clazz);
                    } else {
                        map.put(data.name(), clazz);
                    }
                    classList.add(map);
                }
            }
            if (classList.size() == 0) return;
            schema = new ArrayList<>();
            for (Map<String, Class<?>> m : classList) {
                for (Map.Entry<String, Class<?>> e : m.entrySet()) {
                    Field[] fields = e.getValue().getDeclaredFields();
                    Map<String, String> map = new LinkedHashMap<>();
                    for (Field f : fields) {
                        if (f.isAnnotationPresent(Id.class)) {
                            map.put(f.getName(), e.getKey() + ".Id");
                        }
                        if (f.isAnnotationPresent(Column.class)) {
                            map.put(f.getName(), e.getKey() + ".Column");
                        }
                    }
                    if (map.size() != 0) schema.add(map);
                }
            }
        }
    }

    @Override
    public void insert(Object object) throws NoSchemaException {
        if (schema == null) throw new NoSchemaException();
        System.out.println("INSERT");
    }

    @Override
    public void update(Object object) throws NoSchemaException {
        if (schema == null) throw new NoSchemaException();
        System.out.println("UPDATE");
    }

    @Override
    public void delete(Object object) throws NoSchemaException {
        if (schema == null) throw new NoSchemaException();
        System.out.println("DELETE");
    }
}