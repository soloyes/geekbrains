package ru.geekbrains.singleton;

public class Singleton {
    private static Singleton INSTANCE;

    private Singleton() {
    }

    public synchronized static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
}
