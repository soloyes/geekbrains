package ru.geekbrains.racing.other;

public class Moto implements Transport {
    public void start(AnotherHuman ah) {
        System.out.println(ah.getName() + " start moto");
    }

    public void end(AnotherHuman ah) {
        System.out.println(ah.getName() + " stop moto");
    }
}
