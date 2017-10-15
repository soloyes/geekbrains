package ru.geekbrains;

class Cat extends Animal {

    Cat(int runDistanceLimit, int swimDistanceLimit, double jumpLimit) {
        super(runDistanceLimit, swimDistanceLimit, jumpLimit);
    }

    @Override
    void run(int distance) {
        System.out.println(distance <= super.runDistanceLimit);
    }

    @Override
    void swim(int distance) {
        System.out.println(false);
    }

    @Override
    void jump(double hight) {
        System.out.println(hight <= super.jumpLimit);
    }
}
