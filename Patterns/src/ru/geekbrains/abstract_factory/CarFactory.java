package ru.geekbrains.abstract_factory;

public class CarFactory implements PartsFactory {

    @Override
    public Window createWindows() {
        return new CarWindow();
    }

    @Override
    public Wheel createWheels() {
        return new CarWheel();
    }

    @Override
    public Chair createChairs() {
        return new CarChair();
    }
}
