package ru.geekbrains.iterator;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.putRight(i);
        }

        System.out.println(linkedList.getIterator());
        for ( MyLinkedList.Iterator i = linkedList.getIterator(); i.hasNext();) {
            System.out.println(i.next());
        }
        System.out.println(linkedList);
    }
}
