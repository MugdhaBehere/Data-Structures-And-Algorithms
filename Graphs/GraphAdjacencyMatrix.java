package Graphs;
import java.util.*;
import java.io.*;

class GraphAdjacencyMatrix {
    int vertex;
    int matrix[][];

    public GraphAdjacencyMatrix(int vertex) {
        this.vertex = vertex;
        matrix = new int[vertex][vertex];
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1;
    }

    public void printGraph() {
        System.out.println("Graph Adjacency Matrix:");
        for (int j = 0; j < vertex; j++) {
            for (int k = 0; k < vertex; k++) {
                System.out.print(matrix[j][k] + " ");
            }
            System.out.println();
        }
        for (int j = 0; j < vertex; j++) {
            System.out.print("Vertex" + " " + j + " " + "is connected to" + " ");
            for (int k = 0; k < vertex; k++) {
                if (matrix[j][k] == 1) {
                    System.out.print(k + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.printGraph();
    }

}