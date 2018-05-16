package ru.geekbrains;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        for (int i = 0; i < 30; i++) {
            hashTable.insert(new Item(i));
        }
        System.out.println(hashTable);
        System.out.println("Data: " + hashTable.find(12).getData());
        System.out.println();
        hashTable.delete(0);
        System.out.println(hashTable);
        System.out.println();
        hashTable.delete(12);
        System.out.println(hashTable);
        System.out.println();
        hashTable.insert(new Item(0));
        System.out.println(hashTable);
    }

    private ArrayList<List<Item>> list = new ArrayList<>();

    public HashTable(int size) {
        for (int i = 0; i < size; i++) {
            list.add(new LinkedList<>());
        }
    }

    public void insert(Item item){
        int key = item.getData();
        int hash = hash(key);
        list.get(hash).add(item);
    }

    public Item find(int key){
        int hash = hash(key);
        return hash > list.get(hash).size() ? new Item(-1) : list.get(hash).get(hash);
    }

    public Item delete(int key){
        int hash = hash(key);
        if (hash > list.get(hash).size()) return new Item(-1);
        else return list.get(hash).remove(hash);
    }

    public int hash(int value) {
        return value % list.size();
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<Item> l:list) {
            for (Item i:l) {
                stringBuilder.append(i.getData()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder
                .delete(
                        stringBuilder.length()-2,
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
