package ru.geekbrains;

class Dog extends Animal {

    Dog(int runDistanceLimit, int swimDistanceLimit, double jumpLimit) {
        super(runDistanceLimit, swimDistanceLimit, jumpLimit);
    }

    @Override
    void run(int distance) {
        System.out.println(distance <= super.runDistanceLimit);
    }

    @Override
    void swim(int distance) {
        System.out.println(distance <= super.swimDistanceLimit);
    }

    @Override
    void jump(double high) {
        System.out.println(high <= super.jumpLimit);
    }
}
