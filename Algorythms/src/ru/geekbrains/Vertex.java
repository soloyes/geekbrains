package ru.geekbrains;

import lombok.Data;

@Data
public class Vertex<T> {
    private T payload;
    private boolean visited;
    private int weight;

    public Vertex(T payload) {
        this.payload = payload;
        this.visited = false;
        this.weight = -1;
    }
}
