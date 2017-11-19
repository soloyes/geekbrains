package ru.geekbrains.racing.other;

public class AnotherHuman {
    private String name;

    public String getName() {
        return name;
    }

    public AnotherHuman(String name) {
        this.name = name;
    }

    public void start(Transport t) {
        t.start(this);
    }

    public void stop(Transport t) {
        t.end(this);
    }
}
