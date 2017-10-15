package ru.geekbrains;

class Truck extends Vehicle {

    private final int TRUCK_WEIGHT_LIMIT = 2000;

    public Truck(int velocity, int weight, String name) {
        super(velocity, weight, name);
    }

    int getVelocity() {
        return super.velocity;
    }

    String getName() {
        return super.name;
    }

    int getWeight() {
        return super.weight;
    }

    @Override
    void carryingCapacity(int freight) {
        if (freight <= TRUCK_WEIGHT_LIMIT)
            System.out.println(getName() + " freight is " + (getWeight() +freight));
    }
}
