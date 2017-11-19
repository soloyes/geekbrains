package ru.geekbrains.racing.other;

public class Car implements Transport {
    public void start(AnotherHuman ah) {
        System.out.println("Human car drive");
        ride();
    }

    public void end(AnotherHuman ah) {
        System.out.println("Human car end");
    }

    public void ride() {

    }
}
