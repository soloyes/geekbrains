package ru.geekbrains;

class Cart extends Vehicle {

    public Cart(int velocity, int weight, String name) {
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
    void moveBack(int time) {
        System.out.println(getName() + " can't move back");;
    }

    @Override
    void carryingCapacity(int freight) {
        System.out.println(getName() + " can't carry anything");
    }
}
