package ru.geekbrains;

import java.util.Arrays;

public class Deque {

    /**
     *
     * 1   2   3   4   5   6
     * head    tail
     */

    private int[] array;
    private int size;
    private int head;
    private int tail;
    private int capacity;

    public Deque(int size) {
        this.size = size;
        array = new int[size];
        capacity = 0;
        head = 0;
        tail = -1;
    }

    public int getSize() {
        return size;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void insertLeft(int i) {
        if (isFull()) throw new FullDeque();
        if (--head < 0) {
                head = size - 1;
        }
        array[head] = i;
        capacity++;
    }

    public int removeLeft() {
        if (++head > size - 1)
            head = 0;
        capacity--;
        return array[head - 1];
    }

    public void insertRight(int i) {
        if (isFull()) throw new FullDeque();
        if (++tail > size){
                tail = 0;
        }
        array[tail - 1] = i;
        capacity++;
    }

    public int removeRight() {
        if (--tail < 0) tail = size - 1;
        capacity--;
        return array[tail];
    }

    @Override
    public String toString() {
        return "Deque{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
