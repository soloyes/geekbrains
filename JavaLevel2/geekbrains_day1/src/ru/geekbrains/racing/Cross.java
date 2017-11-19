package ru.geekbrains.racing;

public class Cross extends Obstacle {
    private int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor c) {
        c.run(length);
    }
}
