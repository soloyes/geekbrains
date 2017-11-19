package ru.geekbrains.racing;

public class Water extends Obstacle {
    private int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor c) {
        c.swim(length);
    }
}
