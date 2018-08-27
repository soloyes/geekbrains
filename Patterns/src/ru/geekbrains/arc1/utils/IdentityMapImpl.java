package ru.geekbrains.arc1.utils;

import java.util.ArrayList;
import java.util.List;

public class IdentityMapImpl implements IdentityMap {
    private static List<Object> cacheEmulation;

    public IdentityMapImpl() {
        cacheEmulation = new ArrayList<>();
    }

    @Override
    public boolean exists(Object object) {
        return cacheEmulation.contains(object);
    }

    @Override
    public void add(Object object) {
        cacheEmulation.add(object);
    }

    @Override
    public void remove(Object object) {
        cacheEmulation.remove(object);
    }

    @Override
    public String toString() {
        return cacheEmulation.toString();
    }
}
