package ru.geekbrains.fabric_method;

public class Car implements Transport {
    @Override
    public String deliver() {
        return "by Car";
    }
}
