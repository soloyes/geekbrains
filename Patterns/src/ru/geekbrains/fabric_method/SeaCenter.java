package ru.geekbrains.fabric_method;

public class SeaCenter implements LogisticCenter {
    @Override
    public Transport getTransport() {
        return new Ship();
    }
}
