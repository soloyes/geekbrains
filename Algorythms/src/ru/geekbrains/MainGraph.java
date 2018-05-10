package ru.geekbrains;

public class MainGraph {

    /*
    Реализовать программу, в которой задается граф из 10 вершин.
    Задать ребра и попробовать найти минимальный кратчайший путь с помощью поиска в ширину.
    */

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(10);
        for (int i = 0; i < graph.getSize(); i++) {
            graph.addVertex(i);
        }

        graph.addAdjacency(0, 1);
        graph.addAdjacency(1, 2);
        graph.addAdjacency(0, 3);
        graph.addAdjacency(3, 6);
        graph.addAdjacency(4, 7);
        graph.addAdjacency(4, 5);
        graph.addAdjacency(7, 8);
        graph.addAdjacency(6, 9);
        graph.addAdjacency(6, 7);
        graph.addAdjacency(5, 2);

        graph.depthTravers();
        graph.widthTravers(true, null);
        System.out.println(graph.getWay(graph.getVertex(7)));
    }
}
