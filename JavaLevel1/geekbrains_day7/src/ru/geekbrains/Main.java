package ru.geekbrains;

import java.util.Random;

public class Main {
    public static void main(String[] args){
        Plate plate = new Plate(70);
        Cat[] cats = new Cat[new Random().nextInt(11) + 1];

        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat(new Random().nextInt(30), "Cat" + i);
        }

        for (Cat c : cats){
            c.eat(plate);
        }

        Cat.catInfo(cats);

        plate.addFood(100);
    }
}