package ru.geekbrains.arc1.utils;

import java.util.List;

public interface ClassLoader {
    List<Class<?>> load(String scannedPackage);
}
