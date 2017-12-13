package ru.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<E extends Fruit> {
    private ArrayList<E> box = new ArrayList<>();

    public ArrayList<E> getBox() {
        return box;
    }

    public void put (E[] arr){
        box.addAll(Arrays.asList(arr));
    }

    public static <T extends Fruit> double getWeight (Box<T> box){
        double r = 0.0;
        for (int i = 0; i < box.getBox().size(); i++) {
            r += box.getBox().get(i).getWeight();
        }
        return r;
    }

    public <T extends Fruit> boolean compare(Box<T> box){
        return Box.getWeight(this) == Box.getWeight(box);
    }

    public void giveMeMore(Box<E> box){
        if (box.equals(this)) return;
        this.box.addAll(box.getBox());
        box.getBox().clear();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (E e:box) {
            stringBuilder.append(e.toString());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
