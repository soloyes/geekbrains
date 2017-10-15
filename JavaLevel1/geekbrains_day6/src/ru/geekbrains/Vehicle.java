package ru.geekbrains;

class Vehicle {
    int velocity;
    int weight;
    String name;

    public Vehicle(){
    }

    public Vehicle(int velocity, int weight, String name) {
        this.velocity = velocity;
        this.weight = weight;
        this.name = name;
    }

    void moveForward(int time){
        System.out.println(name + " forward distance is: " + time*velocity);
    }

    void moveBack(int time){
        System.out.println(name + " backward distance is: " + time*velocity);
    }

    void carryingCapacity(int freight){

    }
}
