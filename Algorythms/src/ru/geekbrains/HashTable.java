package ru.geekbrains;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable {

    private ArrayList<List<Item>> list = new ArrayList<>();

    public HashTable(int size) {
        for (int i = 0; i < size; i++) {
            list.add(new LinkedList<>());
        }
    }

    public void insert(Item item) {
        int key = item.getData();
        int hash = hash(key);
        list.get(hash).add(item);
    }

    public Item find(int key) {
        int hash = hash(key);
        return hash > list.get(hash).size() ? new Item(-1) : list.get(hash).get(hash);
    }

    public Item delete(Item item) {
        int hash = hash(item.getData());
        for (int i = 0; i < list.get(hash).size(); i++) {
            if (list.get(hash).get(i).getData() == item.getData()) return list.get(hash).remove(i);
        }
        return new Item(-1);
    }

    public int hash(int value) {
        return value % list.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<Item> l : list) {
            for (Item i : l) {
                stringBuilder.append(i.getData()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder
                .delete(
                        stringBuilder.length() - 2,
                        stringBuilder.length()
                )
                .toString();
    }

    static class Item {
        private int data;

        public Item(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }
}
