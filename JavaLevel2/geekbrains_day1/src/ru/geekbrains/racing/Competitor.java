package ru.geekbrains.racing;

public interface Competitor {
    String getName();
    void run(int distance);
    void jump(int height);
    void swim(int distance);
    boolean isOnDistance();
}
