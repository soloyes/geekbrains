package ru.geekbrains;

class Plate {
    private int food;
    Plate(int food){
        this.food = food;
    }

    boolean decreaseFood(int value){
        if (this.food < value) {
            return false;
        }
        this.food -= value;
        return true;
    }

    void addFood(int value){
        this.food += value;
    }
}