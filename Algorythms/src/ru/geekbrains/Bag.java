package ru.geekbrains;

import java.util.LinkedList;
import java.util.List;

public class Bag {
    private int m;
    private Item[] items;
    private List<Integer> list = new LinkedList<>();

    public Bag(int m, Item ... items) {
        this.m = m;
        this.items = items;
    }

    public int getBag(){
        return getBag(items.length - 1, m);
    }

    private int getBag(int i, int m){
       if (i < 0) return 0;
       if (items[i].weight > m)
           return getBag(i-1, m);
       else {
           return Math.max(getBag(i - 1, m), getBag(i - 1, m - items[i].weight) + items[i].cost);
       }
    }

    public static class Item{
        private int weight;
        private int cost;

        public Item(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }
}
