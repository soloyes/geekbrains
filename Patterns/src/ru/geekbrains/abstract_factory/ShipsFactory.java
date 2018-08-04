package ru.geekbrains.abstract_factory;

public class ShipsFactory implements PartsFactory {

    @Override
    public Window createWindows() {
        return new ShipWindow();
    }

    @Override
    public Wheel createWheels() {
        return new ShipWheel();
    }

    @Override
    public Chair createChairs() {
        return new ShipChair();
    }
}
