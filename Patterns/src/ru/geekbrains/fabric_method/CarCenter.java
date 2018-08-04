package ru.geekbrains.fabric_method;

public class CarCenter implements LogisticCenter {
    @Override
    public Transport getTransport() {
        return new Car();
    }
}
