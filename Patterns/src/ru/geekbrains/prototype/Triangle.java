package ru.geekbrains.prototype;

import java.util.Arrays;

public class Triangle extends Shape {
    private Vertex[] vertices;

    @Override
    public Shape clone() {
        return new Triangle(this);
    }

    public Triangle(Vertex... vertices) {
        this.vertices = vertices;
        calculateSquare();
    }

    public Triangle(Triangle triangle) {
        this.vertices = triangle.vertices;
        this.square = triangle.square;
    }

    @Override
    void calculateSquare() {
        square = 0.5f *
                (
                        (vertices[0].x - vertices[2].x) * (vertices[1].x - vertices[2].y)
                                -
                                (vertices[1].x - vertices[2].x) * (vertices[0].y - vertices[2].y)
                );
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + Arrays.toString(vertices) +
                ", square=" + square +
                '}';
    }
}
