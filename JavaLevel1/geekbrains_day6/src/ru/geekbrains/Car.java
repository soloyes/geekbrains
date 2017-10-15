package ru.geekbrains;

class Car extends Vehicle {

    private final int CAR_WEIGHT_LIMIT = 500;

    public Car(int velocity, int weight, String name) {
        super(velocity, weight, name);
    }

    int getVelocity(){
        return super.velocity;
    }

    String getName(){
        return super.name;
    }

    int getWeight(){
        return super.weight;
    }

    @Override
    void carryingCapacity(int freight) {
        if (freight <= CAR_WEIGHT_LIMIT)
            System.out.println(getName() + " freight is " + (getWeight() + freight));
    }
}
