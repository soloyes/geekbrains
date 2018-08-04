package ru.geekbrains.fabric_method;

public class Ship implements Transport {
    @Override
    public String deliver() {
        return "bySea";
    }
}
