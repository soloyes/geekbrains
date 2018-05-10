package ru.geekbrains;

import java.util.LinkedList;
import java.util.List;

public class Graph<T> {
    private final int MAX_VERTICES;
    private List<Vertex> vertices;
    private int[][] adjMatrix;

    public int getSize() {
        return MAX_VERTICES;
    }

    public Graph(int size) {
        MAX_VERTICES = size;
        vertices = new LinkedList<>();
        adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
    }

    public void addVertex(T label) {
        if (vertices.size() > MAX_VERTICES)
            throw new IndexOutOfBoundsException("Max vertices is " + MAX_VERTICES);
        vertices.add(new Vertex<>(label));
    }

    public Vertex getVertex(int i) {
        return vertices.get(i);
    }

    public void addAdjacency(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void displayVertex(int index) {
        System.out.println(vertices.get(index));
    }

    private int getUnvisitedNeighbor(Vertex vertex) {
        for (int i = 0; i < vertices.size(); i++) {
            if (adjMatrix[vertices.indexOf(vertex)][i] == 1 &&
                    !vertices.get(i).isVisited()) {
                return i;
            }
        }
        return -1;
    }

    private int getPrevious(Vertex vertex) {
        for (int i = 0; i < vertices.size(); i++) {
            if (adjMatrix[vertices.indexOf(vertex)][i] == 1) {
                if (vertex.getWeight() - vertices.get(i).getWeight() == 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void depthTravers() {
        System.out.println("depthTravers:");
        LinkedList<Vertex> stack = new LinkedList<>();
        vertices.get(0).setVisited(true);
        displayVertex(0);
        stack.push(vertices.get(0));
        while (!stack.isEmpty()) {
            int v = getUnvisitedNeighbor(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                vertices.get(v).setVisited(true);
                displayVertex(v);
                stack.push(vertices.get(v));
            }
        }
        refreshFlags();
    }

    public int widthTravers(boolean printable, Vertex target) {
        if (printable) System.out.println("widthTravers:");
        LinkedList<Vertex> queue = new LinkedList<>();
        vertices.get(0).setVisited(true);
        queue.add(vertices.get(0));
        int next;
        int weight = 0;
        vertices.get(0).setWeight(weight);
        if (printable) displayVertex(0);
        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            while ((next = getUnvisitedNeighbor(current)) != -1) {

                vertices.get(next).setWeight(current.getWeight() + 1);
                if (vertices.get(next) == target) return next;


                vertices.get(next).setVisited(true);
                if (printable) displayVertex(next);
                queue.add(vertices.get(next));
            }
        }
        refreshFlags();
        return -1;
    }

    public List<Vertex> getWay(Vertex target) {
        System.out.println("getWay:");
        if (vertices.get(0) == target) {
            System.out.println(target);
            return null;
        }
        LinkedList<Vertex> result = new LinkedList<>();
        int current;
        if ((current = widthTravers(false, target)) >= 0) {
            int previous;
            while ((previous = getPrevious(vertices.get(current))) != -1) {
                current = previous;
                result.push(vertices.get(current));
            }
        } else {
            System.out.println("There is no way!");
            return null;
        }
        return result;
    }

    private void refreshFlags() {
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisited(false);
        }
    }
}
