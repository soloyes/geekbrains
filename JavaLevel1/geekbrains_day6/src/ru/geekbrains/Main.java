package ru.geekbrains;

public class Main {

    public static void main(String[] args) {

        Cat cat = new Cat(200, -1, 2);
        Cat cat1 = new Cat(400, -1, 3);
        Dog dog = new Dog(500, 10,0.5);
        Dog dog1 = new Dog(600, 20, 1);
        //

        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Cart(30,50,"Cart");
        vehicles[1] = new Car(150, 1200, "Car");
        vehicles[2] = new Truck(50, 7000, "Truck");

        //1
//        cat.run(100);cat.run(200);cat.run(300);
//        cat.swim(100);
//        cat.jump(1);cat.jump(2);cat.jump(3);
//        System.out.println();
//        dog.run(400);dog.run(500);dog.run(600);
//        dog.swim(5);dog.swim(10);dog.swim(20);
//        dog.jump(0.1);dog.jump(0.5);dog.jump(1);
//        System.out.println();
//        cat1.run(100);cat1.run(200);cat1.run(300);
//        cat1.swim(100);cat1.jump(1);
//        cat1.jump(2);cat1.jump(3);
//        System.out.println();
//        dog1.run(400);dog1.run(500);dog1.run(600);
//        dog1.swim(5);dog1.swim(10);dog1.swim(20);
//        dog1.jump(0.1);dog1.jump(0.5);dog1.jump(1);
//        System.out.println();

        //2
//        for(Vehicle x : vehicles){
//            x.moveForward(5);
//            x.moveBack(5);
//            x.carryingCapacity(201);
//        }
    }
}
