package ru.geekbrains.prototype;

public abstract class Shape {
    protected double square;

    abstract public Shape clone();

    abstract void calculateSquare();
}
