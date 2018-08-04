package ru.geekbrains.abstract_factory;

public interface PartsFactory {
    Window createWindows();

    Wheel createWheels();

    Chair createChairs();
}
