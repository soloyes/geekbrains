package ru.geekbrains;

abstract class Animal {

    int runDistanceLimit;

    int swimDistanceLimit;

    double jumpLimit;

    Animal(int runDistanceLimit, int swimDistanceLimit, double jumpLimit) {
        this.runDistanceLimit = runDistanceLimit;
        this.swimDistanceLimit = swimDistanceLimit;
        this.jumpLimit = jumpLimit;
    }

    abstract void run(int distance);

    abstract void swim(int distance);

    abstract void jump(double high);
}
