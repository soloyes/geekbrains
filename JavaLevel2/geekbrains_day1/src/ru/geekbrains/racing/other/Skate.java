package ru.geekbrains.racing.other;

public class Skate implements Transport {
    public void start(AnotherHuman ah) {
        System.out.println("Human skate start");
    }

    public void end(AnotherHuman ah) {
        System.out.println("Human skate stop");
    }
}
