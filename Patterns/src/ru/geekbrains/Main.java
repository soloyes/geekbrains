package ru.geekbrains;

import ru.geekbrains.abstract_factory.CarFactory;
import ru.geekbrains.abstract_factory.PartsFactory;
import ru.geekbrains.abstract_factory.ShipsFactory;
import ru.geekbrains.bulder.Child;
import ru.geekbrains.bulder.Life;
import ru.geekbrains.fabric_method.CarCenter;
import ru.geekbrains.fabric_method.LogisticCenter;
import ru.geekbrains.fabric_method.SeaCenter;
import ru.geekbrains.prototype.Triangle;
import ru.geekbrains.prototype.Vertex;
import ru.geekbrains.singleton.Singleton;

import java.util.logging.Logger;

public class Main {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LOGGER.info(Singleton.getInstance().toString());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                LOGGER.info(Singleton.getInstance().toString());
            }
        }).start();

        Life life = Life.getBuilder()
                .build();
        Life life1 = Life.getBuilder()
                .buildAge(10)
                .buildChildren(new Child[10])
                .buildHappiness(null)
                .buildMoney(10)
                .build();
        LOGGER.info(life.toString());
        LOGGER.info(life1.toString());

        Triangle triangle = new Triangle(
                new Vertex(10, 10),
                new Vertex(10, 10),
                new Vertex(10, 10)
        );

        Triangle otherTriangle = (Triangle) triangle.clone();

        LOGGER.info(triangle.toString());
        LOGGER.info(otherTriangle.toString());

        LogisticCenter center = new CarCenter();
        LOGGER.info(center.getTransport().deliver());
        center = new SeaCenter();
        LOGGER.info(center.getTransport().deliver());

        PartsFactory factory = new CarFactory();
        LOGGER.info(factory.createChairs().toString());
        factory = new ShipsFactory();
        LOGGER.info(factory.createChairs().toString());
    }
}
