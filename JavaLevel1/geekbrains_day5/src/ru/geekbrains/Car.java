package ru.geekbrains;

public class Car {
    static Car cars[] = new Car[10];
    private int velocity;
    private int power;
    private String color;
    private float volume;
    private int price;
    private String name;

    void createCar(int velocity, int power, String color, float volume, int price, String name){
        this.velocity = velocity;
        this.power = power;
        this.color = color;
        this.volume = volume;
        this.price = price;
        this.name = name;
        for (int i = 0; i < cars.length; i ++){
            if(cars[i] == null) {
                cars[i] = this;
                break;
            }
        }
    }

    static void move(Car car, int distance){
        System.out.println("Time is: " + distance/car.velocity + " hours");
    }

    static void compareCarsVelociity(Car car1, Car car2){
        if (car1.velocity > car2.velocity) System.out.println("velocity of " + car1.name + " more");
        else System.out.println("velocity of " + car2.name + " more");
    }

    static void printCar(){
        for (int i = 0; i < cars.length; i++) {
            System.out.println(cars[i].name);
        }
    }
}
