package ru.geekbrains;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PriorityQueue {
    private List<Integer> queue;
    private int size;

    public PriorityQueue(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void insert(int value) {
        if (!isEmpty()) {
            int pos = 0;
            for (int i = 0; i < queue.size(); i++) {
                if (value <= queue.get(i)) break;
                pos ++;
            }
            queue.add(pos, value);
        }
        else queue.add(value);
    }

    public int remove() {
        return queue.remove(0);
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "queue=" + queue +
                '}';
    }
}
