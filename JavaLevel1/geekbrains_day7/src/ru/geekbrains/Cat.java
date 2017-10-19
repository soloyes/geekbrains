package ru.geekbrains;

class Cat {
    private int appetite;
    private String name;
    private boolean hungry;

    String getName() {
        return name;
    }

    boolean isHungry() {
        return hungry;
    }

    Cat(int appetite, String name){
        this.appetite = appetite;
        this.name = name;
    }
    void eat(Plate plate){
        this.hungry = plate.decreaseFood(this.appetite);
    }

    static void catInfo(Cat[] cats){
        for (Cat c : cats){
            System.out.println("cat " + c.getName() + " is " + ((c.isHungry()) ? "full" : "hungry"));
        }
    }
}