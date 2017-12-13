package ru.geekbrains;

public abstract class Fruit {
    private float weight;

    public double getWeight() {
        return weight;
    }

    public Fruit(float weight) {
        this.weight = weight;
    }
}
