package ru.geekbrains.racing.other;

public class Main {
    public static void main(String[] args) {
        Transport[] transports = {new Car(), new Skate(), new Moto()};
        AnotherHuman ah = new AnotherHuman("Bob");
        for (Transport o : transports) {
            ah.start(o);
            ah.stop(o);
        }
    }
}
