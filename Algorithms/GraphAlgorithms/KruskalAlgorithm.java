package Algorithms.GraphAlgorithms;

import java.util.*;

public class KruskalAlgorithm {

    static class Graph {
        class Edge implements Comparable<Edge> {
            int src, dest, weight;

            Edge(int s, int d, int w) {
                src = s;
                dest = d;
                weight = w;
            }

            @Override
            public int compareTo(Edge compareEdge) {
                return this.weight - compareEdge.weight;
            }
        }

        private int V, E;
        private Edge[] edges;

        Graph(int v, int e) {
            V = v;
            E = e;
            edges = new Edge[E];
            for (int i = 0; i < e; ++i)
                edges[i] = new Edge(0, 0, 0);
        }

        int find(int[] parent, int i) {
            if (parent[i] == i)
                return i;
            return find(parent, parent[i]);
        }

        void union(int[] parent, int[] rank, int x, int y) {
            int xRoot = find(parent, x);
            int yRoot = find(parent, y);

            if (rank[xRoot] < rank[yRoot])
                parent[xRoot] = yRoot;
            else if (rank[xRoot] > rank[yRoot])
                parent[yRoot] = xRoot;
            else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }

        void kruskalMST() {
            Edge[] result = new Edge[V];
            int e = 0;
            int i = 0;
            for (i = 0; i < V; ++i)
                result[i] = new Edge(0, 0, 0);

            Arrays.sort(edges);

            int[] parent = new int[V];
            int[] rank = new int[V];

            for (int v = 0; v < V; ++v) {
                parent[v] = v;
                rank[v] = 0;
            }

            i = 0;

            while (e < V - 1) {
                Edge nextEdge = edges[i++];

                int x = find(parent, nextEdge.src);
                int y = find(parent, nextEdge.dest);

                if (x != y) {
                    result[e++] = nextEdge;
                    union(parent, rank, x, y);
                }
            }

            System.out.println("Minimum Spanning Tree Edges:");
            int minimumCost = 0;
            for (i = 0; i < e; ++i) {
                System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
                minimumCost += result[i].weight;
            }
            System.out.println("Minimum Cost: " + minimumCost);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 7;
        Graph graph = new Graph(V, E);

        // Add edges
        graph.edges[0] = graph.new Edge(0, 1, 1);
        graph.edges[1] = graph.new Edge(0, 2, 2);
        graph.edges[2] = graph.new Edge(0, 3, 6);
        graph.edges[3] = graph.new Edge(1, 2, 3);
        graph.edges[4] = graph.new Edge(1, 4, 5);
        graph.edges[5] = graph.new Edge(2, 4, 4);
        graph.edges[6] = graph.new Edge(3, 4, 7);

        graph.kruskalMST();
    }
}
